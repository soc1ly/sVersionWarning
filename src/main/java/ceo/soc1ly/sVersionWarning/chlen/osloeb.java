package ceo.soc1ly.sVersionWarning.chlen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.ChatColor;

public final class osloeb {
   private static final Pattern HEX = Pattern.compile("&#([A-Fa-f0-9]{6})(.)?");
   private static final Pattern AMP = Pattern.compile("&x(&[A-Fa-f0-9]){6}(.)?");
   private static final Pattern SEC = Pattern.compile("\\u00A7x(\\u00A7[A-Fa-f0-9]){6}(.)?");

   private osloeb() {
   }

   public static String color(String text) {
      if (text != null && !text.isEmpty()) {
         String ready = fixHex(text);
         return ChatColor.translateAlternateColorCodes('&', ready);
      } else {
         return "";
      }
   }

   private static String fixHex(String text) {
      String value = replaceSimpleHex(text);
      value = replaceSplitHex(value, AMP);
      value = replaceSplitHex(value, SEC);
      return value;
   }

   private static String replaceSimpleHex(String text) {
      Matcher matcher = HEX.matcher(text);
      StringBuffer buffer = new StringBuffer();

      while(matcher.find()) {
         String hex = matcher.group(1);
         String tail = matcher.group(2) == null ? "" : matcher.group(2);
         String var10002 = toSection(hex);
         matcher.appendReplacement(buffer, Matcher.quoteReplacement(var10002 + tail));
      }

      matcher.appendTail(buffer);
      return buffer.toString();
   }

   private static String replaceSplitHex(String text, Pattern pattern) {
      Matcher matcher = pattern.matcher(text);
      StringBuffer buffer = new StringBuffer();

      while(matcher.find()) {
         String block = matcher.group();
         String hex = block.replace("&", "").replace("§", "").replace("x", "");
         String color = hex.length() >= 6 ? hex.substring(0, 6) : hex;
         String tail = hex.length() > 6 ? hex.substring(6) : "";
         String var10002 = toSection(color);
         matcher.appendReplacement(buffer, Matcher.quoteReplacement(var10002 + tail));
      }

      matcher.appendTail(buffer);
      return buffer.toString();
   }

   private static String toSection(String hex) {
      StringBuilder builder = new StringBuilder("§x");

      for(char c : hex.toCharArray()) {
         builder.append('§').append(c);
      }

      return builder.toString();
   }
}
