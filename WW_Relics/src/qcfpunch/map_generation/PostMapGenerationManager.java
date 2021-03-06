package qcfpunch.map_generation;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// I SERIOUSLY DON'T RECOMMEND THE USE OF THIS CODE AS AN EXAMPLE.
// SERIOUSLY, DON'T GO COPYING THIS POST MAP GEN SYSTEM TO YOUR MOD.
// IF YOU WILL, TALK WITH ME FIRST.
// Thanks.
public class PostMapGenerationManager {

	public static boolean initialized = false;
	static int priority_counter;
	static ArrayList<PostMapGenerationChange> post_map_gen_changers; 
	public static final Logger logger = LogManager.getLogger(PostMapGenerationManager.class.getName());
	
	public PostMapGenerationManager() {
	
		if (initialized == false) {
			preparePostMapGenerationManager();
		}

	}

	static void preparePostMapGenerationManager() {
		initialized = true;
		priority_counter = 0;
		post_map_gen_changers = new ArrayList<PostMapGenerationChange>();
	}
	
	public static int getPriorityCounter() {
		priority_counter += 1;
		return priority_counter;
	}
	
	public static void addPostMapGenerationChange(PostMapGenerationChange map_changer) {
		if (initialized == false) {
			preparePostMapGenerationManager();
		}
		post_map_gen_changers.add(map_changer);
	}
	
	public static void doIfPossiblePostMapGenerationChangers() {
		sortPostMapGenerationChangers();
		callAllPostMapGenerationChanges();
		loadCounter();
		cleanPostMapGenerationChanges();
	}
	
	static void sortPostMapGenerationChangers() {
		logger.info("Sorting post map generation changes.");
		Collections.sort(post_map_gen_changers);
		logger.info("Post map generation changes sorted.");
	}
	
	static void callAllPostMapGenerationChanges() {
		logger.info("Calling post map generation changes");
		logger.info("Number of changes listed = " + post_map_gen_changers.size());
		
		int changes_done = 0;
		int changes_not_done = 0;
		
		for (int i = 0; i < post_map_gen_changers.size(); i++) {
			
			if (post_map_gen_changers.get(i).post_map_gen_changer_object.canDoEffectAfterMapGeneration()) {
				post_map_gen_changers.get(i).post_map_gen_changer_object.doEffectAfterMapGeneration();
				changes_done++;
			} else {
				changes_not_done++;
			}
		
			
		}
		logger.info("Post map generation changes made = " + changes_done);
		logger.info("Post map generation changes not made (because it wasn't the time for them = " +
				changes_not_done);
		logger.info("Post map generation changes called.");
	}
	
	static void loadCounter() {
		logger.info("Loading latest post map generation priority priority_counter.");
		if (post_map_gen_changers.size() > 0) {
			int new_counter = post_map_gen_changers.get(post_map_gen_changers.size()-1).counter;
			if (priority_counter < new_counter) priority_counter = new_counter;
		}
		logger.info("Current post map generation priority priority_counter = " + priority_counter);
	}
	
	static void cleanPostMapGenerationChanges() {
		logger.info("Cleaning list of post map generation changes.");
		post_map_gen_changers.clear();
		logger.info("list of post map generation changes cleaned.");
	}
	
		
}