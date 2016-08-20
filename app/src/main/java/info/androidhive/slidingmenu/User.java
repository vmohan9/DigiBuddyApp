package info.androidhive.slidingmenu;

/**
 * Created by divya on 4/9/2016.
 */
public class User {

    String name,emailaddress,username,password,Age,Sex;
    public static int Height,Weight;
    public static float stepcount;
    public static double stepcal;


    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setEmailaddress(String emailaddress)
    {
        this.emailaddress=emailaddress;
    }

    public String getEmailaddress()
    {
        return this.emailaddress;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setAge(String age)
    {
        this.Age=age;
    }

    public String getAge()
    {
        return this.Age;
    }
    public void setSex(String sex)
    {
        this.Sex=sex;
    }

    public String getSex()
    {
        return this.Sex;
    }
    public void setHeight(int height)
    {
        this.Height=height;
    }

    public int getHeight()
    {
        return this.Height;
    }

    public void setWeight(int weight)
    {
        this.Weight=weight;
    }

    public int getWeight()
    {
        return this.Weight;
    }

}
