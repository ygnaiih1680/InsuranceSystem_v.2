package system.insurance.backend.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.insurance.backend.dto.AnnouncementDTO;
import system.insurance.backend.service.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }


    //공지 전체 목록.
    @GetMapping("/info")
    @ResponseBody
    @JsonInclude(Include.NON_NULL)
    public List<AnnouncementDTO> getAllAnnouncement() {
        return this.announcementService.findAll();
    }

    //공지 중 하나.
    @GetMapping("/content")
    @ResponseBody
    public ResponseEntity<AnnouncementDTO> getContent(@RequestParam(name = "id") int id){
        return ResponseEntity.of(this.announcementService.findContent(id));
    }
}
