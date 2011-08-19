package org.jboss.seam.examples.seamcrm.tools;

import java.util.Collection;

import org.apache.commons.lang.text.StrBuilder;

/**
 * Extends StrBuilder to provide an easy way to add new lines to a string
 * 
 * @author Cody Lerum
 * 
 */
public class MultiLineStringBuilder extends StrBuilder
{
   public MultiLineStringBuilder()
   {
      super();
   }

   public MultiLineStringBuilder newLine(String str)
   {
      if (super.length() == 0)
      {
         super.append(str);
      }
      else
      {
         super.appendNewLine();
         super.append(str);
      }
      return this;
   }

   public MultiLineStringBuilder newLines(Collection<String> strs)
   {
      for (String str : strs)
      {
         newLine(str);
      }
      return this;
   }
}
