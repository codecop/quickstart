package org.walkmod.quickstart.model;

import java.util.Date;
import java.util.Calendar;

public class Tweet {
   private User user;

   public String text;

   private Date date;

   public Tweet() {
   }

   public Tweet(User user, String text) {
      this.user = user;
      this.text = text;
      date = new Date();
   }
}
