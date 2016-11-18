package control;

import java.util.HashMap;
import java.util.Map;

import model.Sprinkler;
import model.SprinklerGroup;

public class SprinklerController extends Thread {

	private static final int FORCE_INTERRUPT_DURATION = 3;

	private TimeTemperatureSimulator timeTemperatureSimulator;
	private WaterConsumptionSimulator waterConsumptionSimulator;
	private SprinklerGroup[] sprinklerGroup;
	private Map<Sprinkler, Integer> forceEnabledSprinklers;
	private Map<Sprinkler, Integer> forceDisabledSprinklers;

	public SprinklerController(TimeTemperatureSimulator timeTemperatureSimulator,
			WaterConsumptionSimulator waterConsumptionSimulator, SprinklerGroup[] sprinklerGroup) {
		this.timeTemperatureSimulator = timeTemperatureSimulator;
		this.waterConsumptionSimulator = waterConsumptionSimulator;
		this.forceEnabledSprinklers = new HashMap<Sprinkler, Integer>();
		this.forceDisabledSprinklers = new HashMap<Sprinkler, Integer>();
	}

	public void addForceEnabledSprinkler(Sprinkler sprinkler) {
		this.forceEnabledSprinklers.put(sprinkler, timeTemperatureSimulator.getHour());
	}

	public void addForceDisabledSprinkler(Sprinkler sprinkler) {
		this.forceDisabledSprinklers.put(sprinkler, timeTemperatureSimulator.getHour());
	}

	@Override
	public void run() {

		while (true) {
			int hour = this.timeTemperatureSimulator.getHour();
			double temperature = this.timeTemperatureSimulator.getTemperature();

			for (SprinklerGroup sg : sprinklerGroup) {
				int volumePerHour = 0;
				for (Sprinkler sprinkler : sg.getSprinklers()) {
					if (!sprinkler.isFunctional()) {
						continue;
					}

					// Force interrupt control
					if (forceEnabledSprinklers.get(sprinkler) != null) {
						int interruptHour = forceEnabledSprinklers.get(sprinkler);
						if (hour > (interruptHour + FORCE_INTERRUPT_DURATION) % 24) {
							forceEnabledSprinklers.remove(sprinkler);
						} else {
							continue;
						}
					}
					if (forceDisabledSprinklers.get(sprinkler) != null) {
						int interruptHour = forceDisabledSprinklers.get(sprinkler);
						if (hour > (interruptHour + FORCE_INTERRUPT_DURATION) % 24) {
							forceDisabledSprinklers.remove(sprinkler);
						} else {
							continue;
						}
					}

					// Temperature control
					if (temperature < 55) {
						sprinkler.disableByTemperature();
						volumePerHour = 0;
						continue;
					} else if (temperature > 90) {
						sprinkler.enableByTemperature();
						volumePerHour += 2;
						continue;
					}

					// User control - individual
					if (sprinkler.isOnIndividual()) {
						if (hour > sprinkler.getIndividualSchedule().getEndTime()) {
							sprinkler.disableByUserIndividual();
							volumePerHour -= sprinkler.getIndividualSchedule().getVolumePerHour();
							continue;
						}
					} else {
						if (hour < sprinkler.getIndividualSchedule().getStartTime()) {
							sprinkler.enableByUserIndividual();
							volumePerHour += sprinkler.getIndividualSchedule().getVolumePerHour();
							continue;
						}
					}

					// User control - group
					if (sprinkler.isOnGroup()) {
						if (hour > sprinkler.getGroupSchedule().getEndTime()) {
							sprinkler.disableByUserGroup();
							volumePerHour -= sprinkler.getGroupSchedule().getVolumePerHour();
						}
					} else {
						if (hour < sprinkler.getGroupSchedule().getStartTime()) {
							sprinkler.enableByUserGroup();
							volumePerHour += sprinkler.getGroupSchedule().getVolumePerHour();
						}
					}
				}

				// Set new volume/hour if changed
				if (waterConsumptionSimulator.getVolumePerHour(sg.getLocation()) != volumePerHour) {
					waterConsumptionSimulator.setVolumePerHour(sg.getLocation(), volumePerHour);
				}
			}

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
