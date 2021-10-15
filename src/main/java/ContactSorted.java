import java.util.Comparator;

public class ContactSorted implements Comparator<Contact>
{
    @Override
    public int compare(Contact o1, Contact o2) {
        return o2.firstName.compareToIgnoreCase(o1.firstName);
    }
}
