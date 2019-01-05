package ro.ase.proiect.dam_project.networking;

import java.io.Serializable;
import java.util.List;

public class CategoryJson implements Serializable {

    private List<ContestJson> softSkills;
    private List<ContestJson> technologies;
    private List<ContestJson> fun;

    public CategoryJson(List<ContestJson> softSkills, List<ContestJson> technologies, List<ContestJson> fun) {
        this.softSkills = softSkills;
        this.technologies = technologies;
        this.fun = fun;
    }

    public List<ContestJson> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<ContestJson> softSkills) {
        this.softSkills = softSkills;
    }

    public List<ContestJson> gettechnologies() {
        return technologies;
    }

    public void settechnologies(List<ContestJson> technologies) {
        this.technologies = technologies;
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
                ", technologies=" + technologies +
                ", fun=" + fun +
                '}';
    }

    public CategoryJson() {
    }
}
