package com.cognizant.cms.model;

public class RoleView {

       private String roleName;
       private int userCount = 0;
       private int noOfPermissions = 0;
       private String description;

       public RoleView() {
              super();
       }

       public RoleView(String roleName, int userCount, int noOfPermissions, String description) {
              super();
              this.roleName = roleName;
              this.userCount = userCount;
              this.noOfPermissions = noOfPermissions;
              this.description = description;
       }

       public String getRoleName() {
              return roleName;
       }

       public void setRoleName(String roleName) {
              this.roleName = roleName;
       }

       public int getUserCount() {
              return userCount;
       }

       public void setUserCount(int userCount) {
              this.userCount = userCount;
       }

       public int getNoOfPermissions() {
              return noOfPermissions;
       }

       public void setNoOfPermissions(int noOfPermissions) {
              this.noOfPermissions = noOfPermissions;
       }

       public String getDescription() {
              return description;
       }

       public void setDescription(String description) {
              this.description = description;
       }

       @Override
       public String toString() {
              return "UserDescription [roleName=" + roleName + ", userCount=" + userCount + ", noOfPermissions="
                           + noOfPermissions + ", description=" + description + "]";
       }

}


