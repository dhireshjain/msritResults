package results;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class extractor {

	String extract(String usn) throws IOException
	{
		String name = null , cgpa = null , sgpa = null ;
		
		Document doc = Jsoup.connect("http://result.msrit.edu/sresult.php?myusn="+usn).get();
		Elements el = doc.select(".style6");
		
		boolean flag1  = false , flag2 = false;
		
		for(Element el2 : el)
		{
			String str = el2.textNodes().toString();
			
			if(str.contains("Name:"))
			{
				name = str.substring(str.indexOf(' ')+1,str.length()-1);
			}
			if(str.contains("SGPA]"))
			{
				flag2 = true;
				continue;
			}
			if(flag2)
			{	
				flag2 =false;
				sgpa = str.substring(1,str.length()-1) ;
			}
			if(str.contains("CGPA]"))
			{
				flag1 = true;
				continue;
			}
			if(flag1)
			{	
				flag1=false;
				cgpa = str.substring(1,str.length()-1);
				break;
			}
		}
			
		return name+"#"+cgpa+"#"+sgpa;
	}
	
}
