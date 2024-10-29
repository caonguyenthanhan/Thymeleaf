package vn.iotstar.Model;

public class CategoryModel {
    private Long id;
    private String name;
    private Boolean isEdit = false;

    // Constructor, getter và setter
    public CategoryModel() {
    }

    public CategoryModel(Long id, String name, Boolean isEdit) {
        this.id = id;
        this.name = name;
        this.isEdit = isEdit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
}