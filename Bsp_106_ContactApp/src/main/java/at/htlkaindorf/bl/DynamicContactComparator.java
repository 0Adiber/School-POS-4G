package at.htlkaindorf.bl;

import at.htlkaindorf.beans.Sortings;
import at.htlkaindorf.pojos.Contact;
import java.util.Comparator;
import java.util.List;

public class DynamicContactComparator implements Comparator<Contact>{

    private List<Sortings> sort;
    
    public DynamicContactComparator(List<Sortings> sort) {
        this.sort = sort;
    }
    
    @Override
    public int compare(Contact o1, Contact o2) {
        int res = 0;
        
        for(Sortings s : sort) {
            switch(s) {
                case ID: res = o1.getId() - o2.getId(); break;
                case FIRSTNAME: res = o1.getFirstname().compareTo(o2.getFirstname()); break;
                case LASTNAME: res = o1.getLastname().compareTo(o2.getLastname()); break;
                case GENDER: res = o1.getGender().compareTo(o2.getGender()); break;
                case BIRTH: res = o1.getDateOfBirth().compareTo(o2.getDateOfBirth()); break;
                case COMPANY: res = o1.getCompany().compareTo(o2.getCompany()); break;
            }
            
            if(res != 0)
                break;
        }
        
        return res;
    }

}
