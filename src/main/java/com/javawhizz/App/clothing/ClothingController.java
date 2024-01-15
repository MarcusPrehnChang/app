package com.javawhizz.App.clothing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("api")
public class ClothingController {
    int amountOfTags = 100;
    @PostMapping("/process")
    public ResponseEntity<List<Tag>> processResource(@RequestBody CombinedData data){
        System.out.println("ProcessResource");
        System.out.println("Received request payload: " + data);
        System.out.println(data.getTagList().size() + " taglist size");
        List<Tag> filteredTags = new ArrayList<>();
        String filterData = "";
        String tagData = "";
        for(int i = 0; i < data.getTagList().size(); i++){
            tagData += data.getTagList().get(i).name + " ";
        }
        for(int i = 0; i < data.getFilter().size(); i++){
            filterData += data.getFilter().get(i) + " ";
        }
        int totalWeight = 0; //Total amount of weight distributed.
        int currentWeight = 0; //data.tagList.get(0).value; CurrentWeight to keep track of if statements.
        boolean filters = true;
        List<Tag> answer = new ArrayList<>();
        System.out.println(data.getFilter().size());

        if(data.getFilter().get(0).equals("all") || data.getFilter().get(0).equals("All") || data.getFilter().get(0).equals("any")){
            filters = false; //Check if filters are present.
        }

        if(filters){
            System.out.println("no filters");
            for (int i = 0; i < data.getFilter().size(); i++){ //filter iteration

                boolean found = false;

                for (int j = 0; j < data.getTagList().size(); j++){ //Tag iteration
                    if(data.getFilter().get(i).equals(data.getTagList().get(j).name)){ //tag found
                        found = true;
                        filteredTags.add(data.getTagList().get(j)); //add tag
                        break;
                    }
                }
                if(!found){
                    Tag filterTag = new Tag(data.getFilter().get(i), 100);
                    filteredTags.add(filterTag);
                }

            }
            for (Tag filteredTag : filteredTags) {
                totalWeight += filteredTag.value;
            }
        }else{
            totalWeight = 100*(amountOfTags - data.getTagList().size());
            for (Tag tag : data.getTagList()){
                totalWeight += tag.value;
            }
            filteredTags.addAll(data.getTagList());
        }


        int prevWeight = 0; //0 Til at starte med
        Random random = new Random();
        int randomNumber = 0;
        for(int i = 5; i > 0; i--){
            randomNumber = random.nextInt(totalWeight);

            boolean hit = false;
            for (Tag filteredTag : filteredTags) {
                currentWeight += filteredTag.value;

                if (randomNumber < currentWeight && randomNumber >= prevWeight) {
                    answer.add(filteredTag);
                    hit = true;
                    break;
                }
                prevWeight = currentWeight;
            }
            if(!hit){
                answer.add(new Tag("any", -1));
            }
            currentWeight = 0;
            prevWeight = 0;
        }
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/tags/{amount}")
    public ResponseEntity<?> checkNumber(@PathVariable int amount){
        System.out.println("tags amount");
        if (amount != amountOfTags){
            amountOfTags = amount;
        }
        return ResponseEntity.ok("Updated or same");
    }
}

