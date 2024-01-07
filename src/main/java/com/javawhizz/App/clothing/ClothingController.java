package com.javawhizz.App.clothing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping("api")
public class ClothingController {
    int amountOfTags = 0;
    @PostMapping("/process")
    public ResponseEntity<?> processResource(@RequestBody CombinedData data){
        List<Tag> filteredTags = new ArrayList<>();
        int totalWeight = 0; //Total amount of weight distributed.
        int currentWeight = 0; //data.tagList.get(0).value; CurrentWeight to keep track of if statements.
        boolean filters = true;
        List<Tag> answer = new ArrayList<>();

        if(data.filter.get(0).equals("all") || data.filter.get(0).equals("All")){
            filters = false;
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
            } // we might have to migrate to a different way of handling this, will require getting all tags present on the database, can do that by sending different message in the amount request, and asking for the entire list.

        }



        Random random = new Random();
        int randomNumber = random.nextInt(totalWeight);
        for(int i = 5; i > 0; i--){
            for(int j = 0; j < filteredTags.size(); j++){
                currentWeight += filteredTags.get(j).value;
                if(randomNumber < currentWeight){
                    answer.add(filteredTags.get(j));
                    break;
                }
            }
            for(int k = 0; k < unfilteredTags.size(); k++){

            }
        }

    }

    @GetMapping("/tags/{amount}")
    public ResponseEntity<?> checkNumber(@PathVariable int amount){
        if (amount != amountOfTags){
            amountOfTags = amount;
        }
        return ResponseEntity.ok("Updated or same");
    }
}

