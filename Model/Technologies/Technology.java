package Model.Technologies;

public class Technology {

    private boolean underResearch;
    private int costsForResearch;
    private TechnologyTypes technologyType;
    private boolean isAvailabe;


    public Technology(boolean underResearch, int costsForResearch, TechnologyTypes technologyType, boolean isAvailabe) {
        this.underResearch = underResearch;
        this.costsForResearch = costsForResearch;
        this.technologyType = technologyType;
        this.isAvailabe = isAvailabe;
    }

 

    public boolean isIsAvailabe() {
        return this.isAvailabe;
    }

    public boolean getIsAvailabe() {
        return this.isAvailabe;
    }

    public void setIsAvailabe(boolean isAvailabe) {
        this.isAvailabe = isAvailabe;
    }

    public boolean isUnderResearch() {
        return this.underResearch;
    }

    public boolean getUnderResearch() {
        return this.underResearch;
    }

    public void setUnderResearch(boolean underResearch) {
        this.underResearch = underResearch;
    }

    public int getCostsForResearch() {
        return this.costsForResearch;
    }

    public void setCostsForResearch(int costsForResearch) {
        this.costsForResearch = costsForResearch;
    }

    public TechnologyTypes getTechnologyType() {
        return this.technologyType;
    }

    public void setTechnologyType(TechnologyTypes technologyType) {
        this.technologyType = technologyType;
    }

    @Override
    public String toString() {
        return "{" +
            "costsForResearch='" + getCostsForResearch() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", isAvailabe='" + isIsAvailabe() + "'" +
            ", leads to following technologies='" + getTechnologyType().getTechnologyUnlocks() + "'" +
            "}";
    }
    
    
}
