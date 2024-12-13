package studio.magemonkey.fabled.enchants.api;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import studio.magemonkey.fabled.enchants.FabledEnchants;

import java.util.Objects;

/**
 * FabledEnchants © 2024 MageMonkeyStudio
 * api.studio.magemonkey.fabled.enchants.Tasks
 * <p>
 * Utility class providing ways to register tasks through FabledEnchants
 */
public class Tasks {

    private static FabledEnchants plugin = FabledEnchants.getPlugin(FabledEnchants.class);

    /**
     * Schedules a task to run next tick
     *
     * @param runnable runnable to execute
     * @return task handling the runnable
     */
    public static BukkitTask schedule(final Runnable runnable) {
        return schedule(runnable, 1);
    }

    /**
     * Schedules a task to run after the specified number of ticks
     *
     * @param runnable runnable to execute
     * @param delay    number of ticks to wait
     * @return task handling the runnable
     */
    public static BukkitTask schedule(final Runnable runnable, final int delay) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");

        return plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }

    /**
     * Schedules a task to run continuously
     *
     * @param runnable runnable to execute
     * @param delay    delay in ticks before running the task the first time
     * @param interval time in ticks between each subsequent execution
     * @return task handling the runnable
     */
    public static BukkitTask schedule(final Runnable runnable, final int delay, final int interval) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");

        return plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, delay, interval);
    }

    /**
     * Schedules a task to run a given number of times
     *
     * @param runnable    runnable to execute
     * @param delay       delay in ticks before running the task the first time
     * @param interval    time in ticks between each subsequent execution
     * @param repetitions number of times the task should run
     * @return task handling the runnable
     */
    public static BukkitTask schedule(final Runnable runnable, final int delay, final int interval, int repetitions) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");

        return new RepeatTask(runnable, repetitions).runTaskTimer(plugin, delay, interval);
    }

    private static class RepeatTask extends BukkitRunnable {
        private final Runnable runnable;
        private       int      repetitions;

        public RepeatTask(final Runnable runnable, final int repetitions) {
            this.runnable = runnable;
            this.repetitions = repetitions;
        }

        @Override
        public void run() {
            runnable.run();
            repetitions--;
            if (repetitions <= 0) {
                cancel();
            }
        }
    }
}
