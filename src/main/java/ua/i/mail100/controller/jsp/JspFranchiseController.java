package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.i.mail100.config.FileConfig;
import ua.i.mail100.dto.FranchiseDTO;
import ua.i.mail100.mapper.MapperFranchiseUtil;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.service.*;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("franchise")
public class JspFranchiseController {

    @Autowired
    UserService userService;

    @Autowired
    FranchiseService franchiseService;

    @Autowired
    JspService jspService;

    @Autowired
    FileService fileService;

    @Autowired
    FileConfig fileConfig;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @Autowired
    MapperFranchiseUtil mapperFranchiseUtil;

    @Autowired
    DateService dateService;


    @GetMapping(path = "open-all")
    public String openFranchises(Model model,
                                 @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        franchisesMake(model);
        return "franchises";
    }

    @GetMapping("open-for-edit")
    public String openForEdit(Model model,
                              @RequestParam(value = "userId") String userId,
                              @RequestParam(value = "franchiseId") String franchiseId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!franchiseCheckAndMake(model, franchiseId)) return "franchises";
        return "franchise-edit";
    }


    @GetMapping("open-for-save")
    public String openForEdit(Model model,
                              @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        return "franchise-save";
    }

    @PostMapping(path = "update",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String update(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "franchiseId") String franchiseId,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "file") MultipartFile file) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!franchiseCheckAndMake(model, franchiseId)) return "franchises";

        Franchise franchise = franchiseService.getById(Integer.valueOf(franchiseId));
        franchise.setName(name);
        if (!franchiseService.noFranchiseWithSameName(franchise)) {
            model.addAttribute("franchise", franchise);
            model.addAttribute("message", "Name not unique!");
            return "franchise-edit";
        }

        if (!file.isEmpty()) {
            File savedFile = fileService.getFileUploaded(fileConfig.FILE_ROOT_PATH, file);
            if( savedFile == null) {
                model.addAttribute("message", "File not uploaded!");
                return "franchise-edit";
            }
            franchise.setPath(savedFile.getName());
            MultipartFile multipartFile = fileService.convertToMultipartFile(
                    savedFile.getAbsolutePath(),
                    savedFile.getName());
            model.addAttribute("logoFile", multipartFile);
        } else {
            franchise.setPath(null);
        }

        Franchise updatedFranchise = franchiseService.update(franchise);
        if (!franchiseCheckAndMake(model, updatedFranchise)) return "franchise-edit";
        franchisesMake(model);
        return "franchises";
    }

    @PostMapping("save")
    public String save(Model model,
                       @RequestParam(value = "userId") String userId,
            @RequestParam(value = "name") String name,
                       @RequestParam(value = "file") MultipartFile file) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";

        Franchise franchise = new Franchise(null, name, null, null,
                null, null);
        if (!franchiseService.noFranchiseWithSameName(franchise)) {
            model.addAttribute("message", "Name not unique!");
            return "franchise-save";
        }

        if (!file.isEmpty()) {
            File savedFile = fileService.getFileUploaded(fileConfig.FILE_ROOT_PATH, file);
            if( savedFile == null) {
                model.addAttribute("message", "File not uploaded!");
                return "franchise-save";
            }
            franchise.setPath(savedFile.getName());
            MultipartFile multipartFile = fileService.convertToMultipartFile(
                    savedFile.getAbsolutePath(),
                    savedFile.getName());
            model.addAttribute("logoFile", multipartFile);
        } else {
            franchise.setPath(null);
        }

        Franchise savedFranchise = franchiseService.save(franchise);
        if (!franchiseCheckAndMake(model, savedFranchise)) return "franchise-save";
        franchisesMake(model);
        return "franchises";
    }

    @PostMapping("delete")
    public String delete(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "franchiseId") String franchiseId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!franchiseCheckAndMake(model, franchiseId)) return "franchises";

        try {
            franchiseService.deleteById(Integer.valueOf(franchiseId));
        } catch (Exception e) {
            model.addAttribute("message", "Record cannot be deleted!");
            e.printStackTrace();
            return "franchise-edit";
        }
        franchisesMake(model);
        return "franchises";
    }

    public boolean franchiseCheckAndMake(Model model, Franchise franchise) {
        if (franchise != null) {
            FranchiseDTO franchiseDTO = mapperFranchiseUtil.toDTO(franchise);
            model.addAttribute("franchise", franchiseDTO);
            return true;
        } else {
            model.addAttribute("message", "Record are wrong!");
            return false;
        }
    }

    public boolean franchiseCheckAndMake(Model model, String franchiseId) {
        Integer franchiseIdSelected = Integer.valueOf(franchiseId);
        Franchise franchise = franchiseService.getById(franchiseIdSelected);
        return franchiseCheckAndMake(model, franchise);
    }

    public void franchisesMake(Model model) {
        List<Franchise> franchises = franchiseService.getAll(0L);
        List<FranchiseDTO> franchiseDTOS = mapperFranchiseUtil.toDTOList(franchises);
        model.addAttribute("franchises", franchiseDTOS);
    }
}
