package com.nepis.body.building.controller.Impl;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import com.nepis.body.building.controller.WorkOutController;

@Component("workOutControllerImpl")
public class WorkOutControllerImpl implements WorkOutController {

	@Override
	public Response getDailyWorkout(HttpHeaders httpHeaders, String uid)  {
		try {
			return Response.ok().entity(dummyValues()).build();
		} catch (Exception exe) {
			return Response.status(400).entity("Remote Server Error").build();
		}
	}
	@Override
	public Response getBackWorkout(HttpHeaders httpHeaders, String uid)  {
		try {
			return Response.ok().entity(BackWorkout()).build();
		} catch (Exception exe) {
			return Response.status(400).entity("Remote Server Error").build();
		}
	}
	
	public JSONObject dummyValues() throws Exception {
		JSONObject result = new JSONObject();
		JSONArray dailyExercise = new JSONArray();
		JSONObject dailyExerciseSub = new JSONObject();
		JSONArray exercise = new JSONArray();
		JSONObject exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Dumbbell shrug");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/96/Male/l/96_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/96/Male/l/96_2.jpg");
		exerciseSub.put("videoUrl", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/96/Male/l/96_2.jpg");
		exerciseSub.put("muscleGroup", "Traps");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Barbell shrugs");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/97/Male/l/97_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/97/Male/l/97_2.jpg");
		exerciseSub.put("muscleGroup", "Traps");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Behind the back barbell shrugs");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/216/Male/l/216_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/216/Male/l/216_2.jpg");
		exerciseSub.put("muscleGroup", "Traps");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Standing dumbbell up-right row");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/368/Male/l/368_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/368/Male/l/368_2.jpg");
		exerciseSub.put("muscleGroup", "Traps");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Cable shrugs");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/133/Male/l/133_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/133/Male/l/133_2.jpg");
		exerciseSub.put("muscleGroup", "Traps");
		exercise.put(exerciseSub);
		dailyExerciseSub.put("exercise", exercise);
		dailyExerciseSub.put("muscleGroup", "Hamstrings");
		dailyExercise.put(dailyExerciseSub);
		result.put("dailyExercise", dailyExercise);
		return result;
		
	}
	
	public JSONObject BackWorkout() throws Exception {
		JSONObject result = new JSONObject();
		JSONArray dailyExercise = new JSONArray();
		JSONObject dailyExerciseSub = new JSONObject();
		JSONArray exercise = new JSONArray();
		JSONObject exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Wide grip lat pull-down");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/10/Male/l/10_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/10/Male/l/10_2.jpg");
		exerciseSub.put("videoUrl", "http://videocdn.bodybuilding.com/video/mp4/40000/41441m.mp4");
		exerciseSub.put("muscleGroup", "Back");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Barbell deadlift");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/112/Male/l/112_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/112/Male/l/112_2.jpg");
		exerciseSub.put("videoUrl", "http://videocdn.bodybuilding.com/video/mp4/52000/53791m.mp4");
		exerciseSub.put("muscleGroup", "Back");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Bent over barbell row");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/20/Male/l/20_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/20/Male/l/20_2.jpg");
		exerciseSub.put("videoUrl", "http://videocdn.bodybuilding.com/video/mp4/38000/38411m.mp4");
		exerciseSub.put("muscleGroup", "Back");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Lying T-bar row");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/137/Male/l/137_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/137/Male/l/137_2.jpg");
		exerciseSub.put("videoUrl", "http://videocdn.bodybuilding.com/video/mp4/40000/41571m.mp4");
		exerciseSub.put("muscleGroup", "Back");
		exercise.put(exerciseSub);
		exerciseSub = new JSONObject();
		exerciseSub.put("exercuseName", "Reverse flys");
		exerciseSub.put("sets", "5");
		exerciseSub.put("reps", "20/15/12/10/8");
		exerciseSub.put("postion1", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/375/Male/l/375_1.jpg");	
		exerciseSub.put("postion2", "http://www.bodybuilding.com/exercises/exerciseImages/sequences/375/Male/l/375_2.jpg");
		exerciseSub.put("videoUrl", "http://videocdn.bodybuilding.com/video/mp4/40000/40741m.mp4");
		exerciseSub.put("muscleGroup", "Back");
		exercise.put(exerciseSub);
		dailyExerciseSub.put("exercise", exercise);
		dailyExerciseSub.put("muscleGroup", "Back");
		dailyExercise.put(dailyExerciseSub);
		result.put("dailyExercise", dailyExercise);
		return result;
		
	}

}
