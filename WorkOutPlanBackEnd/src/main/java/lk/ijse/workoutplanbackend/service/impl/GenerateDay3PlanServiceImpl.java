package lk.ijse.workoutplanbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.workoutplanbackend.config.JwtFilter;
import lk.ijse.workoutplanbackend.dto.ResponsePlanDTO;
import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.entity.*;
import lk.ijse.workoutplanbackend.repo.*;
import lk.ijse.workoutplanbackend.service.GenerateDay3PlanService;
import lk.ijse.workoutplanbackend.util.CompressionUtil;
import lk.ijse.workoutplanbackend.util.GeminiAiUtil;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GenerateDay3PlanServiceImpl implements GenerateDay3PlanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExercisesRepository exercisesRepository;

    @Autowired
    private GeminiAiUtil geminiAiUtil;

    @Autowired
    private SetsRepository setsRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanDetailsRepository planDetailsRepository;

    @Autowired
    private UserPlanRepository userPlanRepository;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CompressionUtil compressionUtil;

    private Plan plan = new Plan();

    private int index1, index2;

    @Override
    public ResponseUtil generateDay3Plan(UserDTO userDTO) {

        userDTO = updateUser(userDTO);

        List<Exercises> exerciseList = new ArrayList<>();

        String[] shoulders = generateShoulderExercises(userDTO);
        String[] chest = generateChestExercises(userDTO);
        String[] biceps = generateBicepsExercises(userDTO);
        String[] triceps = generateTricepsExercises(userDTO);
        String[] back = generateBackExercises(userDTO);
        String[] legs = generateLegsExercises(userDTO);

        int totalLength = shoulders.length + chest.length + biceps.length + triceps.length + back.length + legs.length;
        String[] exercises = new String[totalLength];

        int index = 0;
        for (String[] arr : new String[][]{chest, triceps, shoulders, biceps, legs, back}) {
            System.arraycopy(arr, 0, exercises, index, arr.length);
            index += arr.length;
        }

        for (String exercise : exercises) {
            List<Exercises> exercisesByName = exercisesRepository.getExercisesByName(exercise);
            if (!exercisesByName.isEmpty() && exercisesByName != null){
                exerciseList.add(exercisesByName.get(0));
            }
        }

        exercises = workPlanChooseSets(exercises,userDTO);

        String[] result = addDays(exercises);

        String str = String.join(",", result);

        try {
            saveData(exerciseList, userDTO, compressionUtil.compress(str));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        exerciseList = addNullValues(exerciseList);

        return new ResponseUtil(200,"3 Day WorkOut Plan", new ResponsePlanDTO(result,exerciseList));

    }

    private List<Exercises> addNullValues(List<Exercises> exerciseList) {
        List<Exercises> newArr = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < exerciseList.size() + 3; i++) {
            if (i == 0 || i == index1 || i == index2) {
                newArr.add(null);
            } else {
                newArr.add(exerciseList.get(j++));
            }
        }
        return newArr;
    }

    private String[] addDays(String[] exercises) {
        String[] exercisesCopy = Arrays.copyOf(exercises, exercises.length);

        if (exercises.length == 18) {
            index1 = 7;
            index2 = 14;
        } else if (exercises.length == 24) {
            index1 = 9;
            index2 = 18;
        } else {
            index1 = 10;
            index2 = 20;
        }

        return setDayToArray(exercisesCopy, index1, index2);
    }

    private String[] setDayToArray(String[] exercises, int index1, int index2) {
        String[] newArray = new String[exercises.length + 3];

        int j = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (i == 0) {
                newArray[i] = "Day 1";
            } else if (i == index1) {
                newArray[i] = "Day 2";
            } else if (i == index2) {
                newArray[i] = "Day 3";
            }else {
                if (j < exercises.length) {
                    newArray[i] = exercises[j++];
                }
            }
        }

        return newArray;
    }

    private UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.getUsersByEmail(jwtFilter.getUserName());
        user.setNowBodyType(userDTO.getNowBodyType());
        user.setWeight(userDTO.getWeight());
        user.setWorkOutTime(userDTO.getWorkOutTime());
        userRepository.save(user);
        return new ModelMapper().map(user, UserDTO.class);
    }

    @Transactional
    protected void saveData(List<Exercises> exerciseList, UserDTO userDTO, String str) {
        List<User> users = userRepository.findByEmail(userDTO.getEmail());
        User user = users.get(0);
        plan.setUser(user);
        plan.setPlanDetails(new ArrayList<>());
        planRepository.save(plan);

        PlanDetails planDetails = new PlanDetails(plan, exerciseList);

        planDetailsRepository.save(planDetails);

        for (Exercises exercise : exerciseList) {
            exercise.setPlanDetails(null);
            exercisesRepository.save(exercise);
        }

        user.setPlanCount(user.getPlanCount() + 1);

        Sets sets = plan.getSets();
        sets.setPlanList(new ArrayList<>());

        setsRepository.save(sets);

        userPlanRepository.save(new UserPlans(str,user));

        userRepository.save(user);
    }

    private String[] workPlanChooseSets(String[] exercises, UserDTO userDTO) {
        if (userDTO.getNowBodyType().equals("Slim")){
            Sets sets = setsRepository.findBySetId(1L);
            plan.setSets(sets);
            return workPlanSetSets(exercises,sets);
        }
        if (userDTO.getNowBodyType().equals("Typical")){
            Sets sets = setsRepository.findBySetId(2L);
            plan.setSets(sets);
            return workPlanSetSets(exercises,sets);
        }
        if (userDTO.getNowBodyType().equals("Plus-Sized")){
            Sets sets = setsRepository.findBySetId(3L);
            plan.setSets(sets);
            return workPlanSetSets(exercises,sets);
        }
        return null;
    }

    private String[] workPlanSetSets(String[] exercises, Sets sets) {
        for (int i = 0; i < exercises.length; i++) {
            int setsCount = sets.getSets();
            int repsCount = sets.getReps();
            exercises[i] = exercises[i] + " " + repsCount + " * " + setsCount;
        }
        return exercises;

    }

    private String[] generateLegsExercises(UserDTO userDTO) {
        List<Exercises> legs = exercisesRepository.findExercisesByBodyPart("Legs");
        return generateExercises(userDTO,legs,"Legs");
    }

    private String[] generateBackExercises(UserDTO userDTO) {
        List<Exercises> back = exercisesRepository.findExercisesByBodyPart("Back");
        return generateExercises(userDTO,back,"Back");
    }

    private String[] generateTricepsExercises(UserDTO userDTO) {
        List<Exercises> triceps = exercisesRepository.findExercisesByBodyPart("Triceps");
        return generateExercises(userDTO,triceps,"Triceps");
    }

    private String[] generateBicepsExercises(UserDTO userDTO) {
        List<Exercises> biceps = exercisesRepository.findExercisesByBodyPart("Biceps");
        return generateExercises(userDTO,biceps,"Biceps");
    }

    private String[] generateChestExercises(UserDTO userDTO) {
        List<Exercises> chest = exercisesRepository.findExercisesByBodyPart("Chest");
        return generateExercises(userDTO,chest,"Chest");
    }

    private String[] generateShoulderExercises(UserDTO userDTO) {
        List<Exercises> shoulders = exercisesRepository.findExercisesByBodyPart("Shoulders");
        return generateExercises(userDTO,shoulders,"Shoulder");
    }

    private String[] generateExercises(UserDTO userDTO, List<Exercises> exercises, String bodyPart) {
        int exercisesCount = 0;
        String prompt = "";
        for (Exercises exercise : exercises) {
            prompt += exercise.getName() + "\n";
        }
        prompt += "\nBody Details:\nNow Body - " + userDTO.getNowBodyType() + "\nGoal Body - " + userDTO.getTargetBodyType() + "\nNow Weight - " + userDTO.getWeight() + " kg\nGoal Weight - " + userDTO.getTargetWeight() + " kg\n";

        if (userDTO.getWorkOutTime() == 30) {
            exercisesCount = 3;
            prompt += "\nGive me " + exercisesCount + " " + bodyPart + " exercises from these exercises that best match this body type.I want you choese " + exercisesCount + " exersices name only.not add any star and any mark";
        } else if (userDTO.getWorkOutTime() == 1) {
            exercisesCount = 4;
            prompt += "\nGive me " + exercisesCount + " " + bodyPart + " exercises from these exercises that best match this body type.I want you choese " + exercisesCount + " exersices name only.not add any star and any mark";
        } else {
            if (bodyPart.equals("Triceps") || bodyPart.equals("Biceps")) {
                exercisesCount = 4;
            } else {
                exercisesCount = 5;
            }
            prompt += "\nGive me " + exercisesCount + " " + bodyPart + " exercises from these exercises that best match this body type.I want you choese " + exercisesCount + " exersices name only.not add any star and any mark";
        }

        return geminiAiUtil.generateResponse(prompt);
    }
}
