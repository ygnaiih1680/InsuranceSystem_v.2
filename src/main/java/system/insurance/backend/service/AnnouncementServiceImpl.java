package system.insurance.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import system.insurance.backend.dbo.announcement.Announcement;
import system.insurance.backend.dbo.employee.Employee;
import system.insurance.backend.exception.NoEmployeeException;
import system.insurance.backend.dto.AnnouncementDTO;
import system.insurance.backend.repository.AnnouncementRepository;
import system.insurance.backend.repository.EmployeeRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<AnnouncementDTO> findAll() {
        List<Announcement> announcements = this.announcementRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return this.createResponseData(announcements);
    }

    @Override
    public List<AnnouncementDTO> findAllByAuthor(int id) throws NoEmployeeException {
        List<Announcement> announcements = this.announcementRepository.findAllByAuthor(this.employeeRepository.findById(id).orElseThrow(NoEmployeeException::new));
        return this.createResponseData(announcements);
    }

    @Override
    public List<AnnouncementDTO> findAllByDate(String date) {
        List<Announcement> announcements = this.announcementRepository.findAllByDate(Date.valueOf(date));
        return this.createResponseData(announcements);
    }

    @Override
    public List<AnnouncementDTO> findAllByTitle(String title) {
        List<Announcement> announcements = this.announcementRepository.findAllByTitle(title);
        return this.createResponseData(announcements);
    }

    @Override
    public Optional<AnnouncementDTO> findContent(int id) {
        Optional<Announcement> announcement = this.announcementRepository.findById(id);
        return announcement.map(value -> AnnouncementDTO.builder()
                .content(value.getContent()).id(id).priority(value.isPriority()).build());
    }

    private List<AnnouncementDTO> createResponseData(List<Announcement> announcements) {
        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        for (Announcement announcement : announcements) {
            Employee author = announcement.getAuthor();
            boolean priority = announcement.isPriority();
            announcementDTOS.add(AnnouncementDTO.builder()
                    .id(announcement.getId())
                    .title(announcement.getTitle())
                    .content(priority ? announcement.getContent() : null)
                    .authorName(author.getAuthority().getAuth())
                    .date(announcement.getDate())
                    .priority(priority)
                    .build());

        }
        return announcementDTOS;
    }
}
