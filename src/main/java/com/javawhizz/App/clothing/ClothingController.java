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
        List<Tag> filteredTags = new ArrayList<>();
        String filterData = "";
        String tagData = "";
        for(int i = 0; i < data.tagList.size(); i++){
            tagData += data.tagList.get(i).name + " ";
        }
        for(int i = 0; i < data.filter.size(); i++){
            filterData += data.filter.get(i) + " ";
        }
        int totalWeight = 0; //Total amount of weight distributed.
        int currentWeight = 0; //data.tagList.get(0).value; CurrentWeight to keep track of if statements.
        boolean filters = true;
        List<Tag> answer = new ArrayList<>();

        if(data.filter.get(0).equals("all") || data.filter.get(0).equals("All") || data.filter.get(0).equals("any")){
            filters = false; //Check if filters are present.
        }

        if(filters){
            for (int i = 0; i < data.filter.size(); i++){ //filter iteration

                boolean found = false;

                for (int j = 0; j < data.tagList.size(); j++){ //Tag iteration
                    if(data.filter.get(i).equals(data.tagList.get(j).name)){ //tag found
                        found = true;
                        filteredTags.add(data.tagList.get(j)); //add tag
                        break;
                    }
                }
                if(!found){
                    Tag filterTag = new Tag(data.filter.get(i), 100);
                    filteredTags.add(filterTag);
                }

            }
            for (Tag filteredTag : filteredTags) {
                totalWeight += filteredTag.value;
            }
        }else{
            totalWeight = 100*(amountOfTags - data.tagList.size());
            for (Tag tag : data.tagList){
                totalWeight += tag.value;
            }
            filteredTags.addAll(data.tagList);
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
                answer.add(new Tag("", -1));
            }
            currentWeight = 0;
            prevWeight = 0;
        }
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/tags/{amount}")
    public ResponseEntity<?> checkNumber(@PathVariable int amount){
        if (amount != amountOfTags){
            amountOfTags = amount;
        }
        return ResponseEntity.ok("Updated or same");
    }
}

