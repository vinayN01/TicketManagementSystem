// @Code_Review: User has more details, right? like mobile number etc
class User {
   private String username;
   private String password;
   private String role; // @Code_Review: this can be an enum, string comparsion is costly

   public User(String username, String password, String role) {
      this.username = username;
      this.password = password;
      this.role = role;
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public String getRole() {
      return role;
   }
}
