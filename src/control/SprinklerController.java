package control;

import model.Sprinkler;
import model.SprinklerGroup;

public class SprinklerController extends Thread {

	private static final int FORCE_INTERRUPT_DURATION_IN_HOUR = 3;
	private static final int TEMPERATURE_ENABLE_VOLUME_PER_HOUR = 5;

	private TimeTemperatureSimulator timeTemperatureSimulator;
	private WaterConsumptionSimulator waterConsumptionSimulator;
	private SprinklerGroup[] sprinklerGroup;

	public SprinklerController(TimeTemperatureSimulator timeTemperatureSimulator,
			WaterConsumptionSimulator waterConsumptionSimulator, SprinklerGroup[] sprinklerGroup) {
		this.timeTemperatureSimulator = timeTemperatureSimulator;
		this.waterConsumptionSimulator = waterConsumptionSimulator;
		this.sprinklerGroup = sprinklerGroup;
	}

	@Override
	public void run() {

		double tempTemperature = 0; // Temporarily holding previous temperature
									// value in temperature-controlled mode.

		while (true) {
			int dayOfWeek = this.timeTemperatureSimulator.getDayOfWeek();
			int hour = this.timeTemperatureSimulator.getHour();
			double temperature = this.timeTemperatureSimulator.getTemperature();

			for (SprinklerGroup sg : sprinklerGroup) {
				int volumePerHour = 0;
				for (Sprinkler sprinkler : sg.getSprinklers()) {
					if (!sprinkler.isFunctional()) {
						continue;
					}

					// Force interrupt control
					if (sprinkler.isForceInterrupted()) {
						if (hour > (sprinkler.getInterruptHour() + FORCE_INTERRUPT_DURATION_IN_HOUR) % 24) {
							sprinkler.disableForceInterrupt();
						} else {
							continue;
						}
					}

					// Temperature control - feature on
					if (!sprinkler.isTemperatureInterrupted()) {
						if (temperature < 55) {
							tempTemperature = temperature;
							sprinkler.disableByTemperature();
							volumePerHour = 0;
							continue;
						} else if (temperature > 90) {
							tempTemperature = temperature;
							sprinkler.enableByTemperature();
							volumePerHour += TEMPERATURE_ENABLE_VOLUME_PER_HOUR;
							continue;
						}
					}

					// Temperature control - feature off
					if (sprinkler.isTemperatureInterrupted()) {
						if (tempTemperature < 55 && temperature >= 55) {
							sprinkler.disableTemperatureInterrupt();
							// Volume/Hour will be set in individual
							// or group section below.
						}
						if (tempTemperature > 90 && temperature <= 90) {
							sprinkler.disableTemperatureInterrupt();
							volumePerHour -= TEMPERATURE_ENABLE_VOLUME_PER_HOUR;
						}
					}

					// User control - individual
					if (sprinkler.isOnIndividual() && sprinkler.isIndividualScheduleSet()) {
						if (sprinkler.isOnIndividual()) {
							if (hour > sprinkler.getIndividualSchedule()[dayOfWeek].getEndTime()) {
								sprinkler.disableByUserIndividual();
								volumePerHour -= sprinkler.getIndividualSchedule()[dayOfWeek].getVolumePerHour();
							}
						} else {
							if (hour >= sprinkler.getIndividualSchedule()[dayOfWeek].getStartTime()
									&& hour <= sprinkler.getIndividualSchedule()[dayOfWeek].getEndTime()) {
								sprinkler.enableByUserIndividual();
								volumePerHour += sprinkler.getIndividualSchedule()[dayOfWeek].getVolumePerHour();
							}
						}
						continue;
					}

					// User control - group
					if (sg.isEnabled() && sprinkler.isGroupScheduleSet()) {
						if (sprinkler.isOnGroup()) {
							if (hour > sprinkler.getGroupSchedule()[dayOfWeek].getEndTime()) {
								sprinkler.disableByUserGroup();
								volumePerHour -= sprinkler.getGroupSchedule()[dayOfWeek].getVolumePerHour();
							}
						} else {
							if (hour >= sprinkler.getGroupSchedule()[dayOfWeek].getStartTime()
									&& hour <= sprinkler.getGroupSchedule()[dayOfWeek].getEndTime()) {
								sprinkler.enableByUserGroup();
								volumePerHour += sprinkler.getGroupSchedule()[dayOfWeek].getVolumePerHour();
							}
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
