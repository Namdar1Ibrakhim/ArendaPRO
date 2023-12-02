package com.example.arendapro.controllers;

import com.example.arendapro.dto.ImmovableImageRequestDto;
import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.dto.StatusDto;
import com.example.arendapro.entity.ImmovableImage;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import com.example.arendapro.enums.Status;
import com.example.arendapro.exceptions.AccessDeniedException;
import com.example.arendapro.model.ImmovableWithCountView;
import com.example.arendapro.entity.User;
import com.example.arendapro.rabbitmq.RabbitMQProducer;
import com.example.arendapro.service.ImmovablesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/immovables")
public class ImmovablesController {

    private final ImmovablesService immovablesService;


    @PostMapping("/add")
    public ResponseEntity<ImmovableResponseDto> addImmovable(@RequestBody @Valid ImmovableRequestDto immovablesDto, @AuthenticationPrincipal User user) throws IOException {
        log.info("Log: " + immovablesDto.toString());
        return ResponseEntity.ok(immovablesService.addImmovable(immovablesDto, user));
    }

    @DeleteMapping("/{immovables_id}")
    public ResponseEntity<String> deleteImmovable(@PathVariable Integer immovables_id, @AuthenticationPrincipal User user) throws Exception {
        return ResponseEntity.ok(immovablesService.deleteImmovable(immovables_id, user));

    }
    @PostMapping("/edit/{immovable_id}")
    public ResponseEntity<ImmovableResponseDto> editImmovable(@RequestBody @Valid ImmovableRequestDto immovableRequestDto, @PathVariable Integer immovable_id, @AuthenticationPrincipal User user) throws AccessDeniedException, IOException {
        return ResponseEntity.ok(immovablesService.editImmovable(immovable_id ,immovableRequestDto, user));
    }

    @GetMapping("")
    public ResponseEntity<ImmovableWithCountView> getByPageAndLimit(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<ImmovableResponseDto> list = immovablesService.getAllActiveImmovables(page, limit);
        return ResponseEntity.ok(new ImmovableWithCountView(list, list.size()));
    }
    @GetMapping("/filteredImmovsbles")
    public List<Immovables> getFilteredImmovables(@RequestParam(required = false) Long minPrice,
                                                  @RequestParam(required = false) Long maxPrice,
                                                  @RequestParam(required = false) Integer minNumOfRooms,
                                                  @RequestParam(required = false) Integer maxNumOfRooms,
                                                  @RequestParam(required = false) Double minArea,
                                                  @RequestParam(required = false) Double maxArea,
                                                  @RequestParam(required = false) State state,
                                                  @RequestParam(required = false) PropertyType propertyType){
        return immovablesService.filterImmovables(
                minPrice, maxPrice, minNumOfRooms, maxNumOfRooms, minArea, maxArea, state, propertyType
        );
    }


    @RequestMapping("/{immovables_id}")
    public ResponseEntity<ImmovableResponseDto> getImmovableById(@PathVariable Integer immovables_id){
        return ResponseEntity.ok(immovablesService.getActiveImmovableById(immovables_id));
    }

    @GetMapping("/myImmovables")
    public ResponseEntity<List<ImmovableResponseDto>> getAllMyImmovables(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(immovablesService.getAllMyImmovables(user));
    }

    @RequestMapping("/byOwner/{owner_id}")
    public ResponseEntity<List<ImmovableResponseDto>> getAllImmovablesByOwner_id(@PathVariable Integer owner_id){
        return ResponseEntity.ok(immovablesService.getActiveImmovablesByOwner(owner_id));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ImmovableResponseDto>> getAll(){
        return ResponseEntity.ok(immovablesService.getAllImmovables());
    }
    @RequestMapping("/changeStatus/{immovable_id}")
    public ResponseEntity changeStatus(@PathVariable Integer immovable_id, @RequestBody @Valid StatusDto statusDto, @AuthenticationPrincipal User user) throws AccessDeniedException {
        immovablesService.changeStatus(immovable_id, statusDto.getStatus(), user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task")
    public ResponseEntity uploadImage(@PathVariable Integer id, @RequestBody MultipartFile image){
        immovablesService.uploadImage(id, image);
        return new ResponseEntity<>(HttpStatus.OK);
     }

}
