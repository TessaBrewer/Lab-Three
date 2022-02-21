package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.HashMap;
import java.lang.Math;

@Controller
public class MyLocationController {
  private HashMap<Integer, MyLocation> myLocations = new HashMap<Integer, MyLocation>();
  
  private int count = 0;
  
  @GetMapping("/")
  public String index() {
    return "index.html";
  }
  
  @GetMapping("/addlocation")
  public String location(Model model) {
    model.addAttribute("myLocation", new MyLocation());
    return "addlocation.html";
  }
  
  @PostMapping("/addlocation")
  public @ResponseBody String addLocation(MyLocation data, HttpServletResponse response) {
    data.setId(count);
    myLocations.put(count, data);
    count++;
    
    try {
      response.sendRedirect("/browselocations");
    } catch(IOException e) {
      e.printStackTrace();
    }
    
    return "";
  }
  
  @GetMapping("/browselocations")
  public String browseLocations(Model model) {
    model.addAttribute("myLocations", myLocations.values());
    return "browseLocations.html";
  }
  
  @GetMapping("/view/{id}")
  public String viewLocations(@PathVariable int id, Model model) {
    model.addAttribute("location", myLocations.get(id));
    return "view.html";
  }
  
  @GetMapping("/findclosestlocation")
  public String findClosestLocation(Model model) {
    model.addAttribute("myLocation", new MyLocation());
    return "findclosestlocation.html";
  }
  
  @PostMapping("/findclosestlocation")
  public String calcClosestLocation(MyLocation data, HttpServletResponse response, Model model) {
    double min = Double.MAX_VALUE;
    MyLocation minLoc = new MyLocation();
    
    for(MyLocation ml : myLocations.values()) {
      double dist = Math.sqrt(Math.pow(data.getXcoord() - ml.getXcoord(), 2) + Math.pow(data.getYcoord() - ml.getYcoord(), 2));
      if(dist < min) {
        minLoc = ml;
        min = dist;
      }
    }
    
    if(myLocations.size() == 0) {
      model.addAttribute("closest", "Please add locations to the database first");
    } else {
      model.addAttribute("closest", minLoc.getXcoord() + ", " + minLoc.getYcoord() + " is the closest location");
    }
    
    return "findclosestlocation.html";
  }
}