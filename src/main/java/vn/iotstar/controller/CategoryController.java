package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iotstar.Model.CategoryModel;
import vn.iotstar.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("add") 

    public String add(ModelMap model) {
        CategoryModel cateModel = new CategoryModel();
        cateModel.setIsEdit(false);
        model.addAttribute("category", cateModel);
        return "admin/categories/addOrEdit";
       
        
    }
}

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    // ... (các phương thức khác)

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
                                    @Valid @ModelAttribute("category") CategoryModel cateModel, 
 BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/categories/addOrEdit");
        }
        Category entity = new Category();
        BeanUtils.copyProperties(cateModel, 
 entity);
        categoryService.save(entity);

        String message = "";
        if (cateModel.getIsEdit() == true) {
            message = "Category is Edited!!!!!!!!";
        } else {
            message = "Category is saved!!!!!!!!";
        }
        model.addAttribute("message", message);

        return new ModelAndView("forward:/admin/categories/searchpaginated", model);
    }
    @RequestMapping("")
    public String list(ModelMap model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories", list);
        return "admin/categories/list"; 

    }
    
    @GetMapping("edit/{categoryId}")
    public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {

        Optional<Category> 
 optCategory = Optional.ofNullable(categoryService.getCategoryById(categoryId));
        CategoryModel cateModel = new CategoryModel();
        if (optCategory.isPresent()) {
            Category entity = optCategory.get();
            BeanUtils.copyProperties(entity, cateModel);
            cateModel.setIsEdit(true);
            model.addAttribute("category", cateModel);
            return new ModelAndView("admin/categories/addOrEdit", model);
        }

        model.addAttribute("message", "Category is not existed!!!!");
        return 
 new ModelAndView("forward:/admin/categories", model); 

    }
    
    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId) {
        categoryService.deleteById(categoryId);
        model.addAttribute("message", "Category is deleted!!!");
        return new ModelAndView("forward:/admin/categories/searchpaginated", model);
    }
    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
        List<Category> list = null;
        if (StringUtils.hasText(name)) {
            list = categoryService.findByNameContaining(name);
        } else {
            list = categoryService.findAll();
        }
        model.addAttribute("categories", list);
        return "admin/categories/search";   

    }

    // ... (các phương thức khác)
}
