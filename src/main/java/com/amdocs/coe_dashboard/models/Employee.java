package com.amdocs.coe_dashboard.models;

import java.util.List;

public class Employee {
    private String empEmail;
    private String empPassword;
    private String empId;
    private String empName; // Name of the employee
    private String currentAccount;
    private String supervisorName; // Supervisor's name in account
    private double amdocsExperience; // Amdocs Experience (years or months)
    private double totalExperience; // Total Experience (years or months)
    private String amdocsJourney; // Amdocs Journey (e.g., roles, growth)
    private List<String> functionalKnowledge; // Functional knowledge or domain expertise
    private List<String> primaryTechSkill; // Primary tech skill (e.g., Java, AWS)
    private List<String> primaryProductSubdomain; // Primary product or subdomain
    private String secondaryTechSkill; // Secondary tech skill (e.g., Python, SQL)
    private String secondaryProduct; // Secondary product knowledge
    private String devOpsKnowledge; // DevOps knowledge (tools, CI/CD, etc.)
    private boolean mentoringAbility; // Can lead/direct 5-6 people
    private boolean explorationInterest; // Likes exploring new tools/tech
    private boolean contributedToDesign; // Contributed to Design/LLD/HLD
    private boolean engagementActivityContribution; // Can help in Engagement activities
    private int presentationSkills; // Presentation skills (on a scale of 1-5)
    private String hobbiesSports; // Hobbies or sports
    private String additionalInfo; // Anything else you want to mention
    private boolean approved;

    public Employee() {
    }

    public Employee(String empEmail, String empPassword, String empId, String empName, String currentAccount, String supervisorName, double amdocsExperience, double totalExperience, String amdocsJourney, List<String> functionalKnowledge, List<String> primaryTechSkill, List<String> primaryProductSubdomain, String secondaryTechSkill, String secondaryProduct, String devOpsKnowledge, boolean mentoringAbility, boolean explorationInterest, boolean contributedToDesign, boolean engagementActivityContribution, int presentationSkills, String hobbiesSports, String additionalInfo, boolean approved) {
        this.empEmail = empEmail;
        this.empPassword = empPassword;
        this.empId = empId;
        this.empName = empName;
        this.currentAccount = currentAccount;
        this.supervisorName = supervisorName;
        this.amdocsExperience = amdocsExperience;
        this.totalExperience = totalExperience;
        this.amdocsJourney = amdocsJourney;
        this.functionalKnowledge = functionalKnowledge;
        this.primaryTechSkill = primaryTechSkill;
        this.primaryProductSubdomain = primaryProductSubdomain;
        this.secondaryTechSkill = secondaryTechSkill;
        this.secondaryProduct = secondaryProduct;
        this.devOpsKnowledge = devOpsKnowledge;
        this.mentoringAbility = mentoringAbility;
        this.explorationInterest = explorationInterest;
        this.contributedToDesign = contributedToDesign;
        this.engagementActivityContribution = engagementActivityContribution;
        this.presentationSkills = presentationSkills;
        this.hobbiesSports = hobbiesSports;
        this.additionalInfo = additionalInfo;
        this.approved = approved;
    }

    public String getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(String currentAccount) {
        this.currentAccount = currentAccount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String name) {
        this.empName = name;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public double getAmdocsExperience() {
        return amdocsExperience;
    }

    public void setAmdocsExperience(double amdocsExperience) {
        this.amdocsExperience = amdocsExperience;
    }

    public double getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(double totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getAmdocsJourney() {
        return amdocsJourney;
    }

    public void setAmdocsJourney(String amdocsJourney) {
        this.amdocsJourney = amdocsJourney;
    }

    public List<String> getFunctionalKnowledge() {
        return functionalKnowledge;
    }

    public void setFunctionalKnowledge(List<String> functionalKnowledge) {
        this.functionalKnowledge = functionalKnowledge;
    }

    public List<String> getPrimaryTechSkill() {
        return primaryTechSkill;
    }

    public void setPrimaryTechSkill(List<String> primaryTechSkill) {
        this.primaryTechSkill = primaryTechSkill;
    }

    public List<String> getPrimaryProductSubdomain() {
        return primaryProductSubdomain;
    }

    public void setPrimaryProductSubdomain(List<String> primaryProductSubdomain) {
        this.primaryProductSubdomain = primaryProductSubdomain;
    }

    public String getSecondaryTechSkill() {
        return secondaryTechSkill;
    }

    public void setSecondaryTechSkill(String secondaryTechSkill) {
        this.secondaryTechSkill = secondaryTechSkill;
    }

    public String getSecondaryProduct() {
        return secondaryProduct;
    }

    public void setSecondaryProduct(String secondaryProduct) {
        this.secondaryProduct = secondaryProduct;
    }

    public String getDevOpsKnowledge() {
        return devOpsKnowledge;
    }

    public void setDevOpsKnowledge(String devOpsKnowledge) {
        this.devOpsKnowledge = devOpsKnowledge;
    }

    public boolean isMentoringAbility() {
        return mentoringAbility;
    }

    public void setMentoringAbility(boolean mentoringAbility) {
        this.mentoringAbility = mentoringAbility;
    }

    public boolean isExplorationInterest() {
        return explorationInterest;
    }

    public void setExplorationInterest(boolean explorationInterest) {
        this.explorationInterest = explorationInterest;
    }

    public boolean isContributedToDesign() {
        return contributedToDesign;
    }

    public void setContributedToDesign(boolean contributedToDesign) {
        this.contributedToDesign = contributedToDesign;
    }

    public boolean isEngagementActivityContribution() {
        return engagementActivityContribution;
    }

    public void setEngagementActivityContribution(boolean engagementActivityContribution) {
        this.engagementActivityContribution = engagementActivityContribution;
    }

    public int getPresentationSkills() {
        return presentationSkills;
    }

    public void setPresentationSkills(int presentationSkills) {
        this.presentationSkills = presentationSkills;
    }

    public String getHobbiesSports() {
        return hobbiesSports;
    }

    public void setHobbiesSports(String hobbiesSports) {
        this.hobbiesSports = hobbiesSports;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}
