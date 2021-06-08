package com.legion.kickstart.Controller;

import com.legion.kickstart.DatabaseEntities.Component;
import com.legion.kickstart.DatabaseService.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/entities")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @PostMapping("/component")
    public String saveComponent(@RequestBody Component component) throws ExecutionException, InterruptedException {
        return componentService.saveComponent(component);
    }

    @GetMapping("/component/{componentId}")
    public Component getComponent(@PathVariable String componentId) throws ExecutionException, InterruptedException {
        return componentService.getComponent(componentId);
    }

    @DeleteMapping("/component/{componentId}")
    public String deleteComponent(@PathVariable String componentId) throws ExecutionException, InterruptedException {
        return componentService.deleteComponent(componentId);
    }
}
