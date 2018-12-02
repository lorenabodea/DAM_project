package ro.ase.proiect.dam_project.networking;

import java.io.Serializable;
import java.util.List;

public class CategoryJson implements Serializable {

    private List<ContestJson> softSkills;
    private List<ContestJson> technoligies;
    private List<ContestJson> fun;

    public CategoryJson(List<ContestJson> softSkills, List<ContestJson> technoligies, List<ContestJson> fun) {
        this.softSkills = softSkills;
        this.technoligies = technoligies;
        this.fun = fun;
    }

    public List<ContestJson> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<ContestJson> softSkills) {
        this.softSkills = softSkills;
    }

    public List<ContestJson> getTechnoligies() {
        return technoligies;
    }

    public void setTechnoligies(List<ContestJson> technoligies) {
        this.technoligies = technoligies;
    }

    public List<ContestJson> getFun() {
        return fun;
    }

    public void setFun(List<ContestJson> fun) {
        this.fun = fun;
    }

    @Override
    public String toString() {
        return "Category{" +
                "softSkills=" + softSkills +
                ", technoligies=" + technoligies +
                ", fun=" + fun +
                '}';
    }

    public CategoryJson() {
    }
}
