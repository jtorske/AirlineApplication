/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.Flights;

public class Location{
        private String Country;
        private String Province;
        private String City;

        public Location(String Country, String Province, String City){
            this.Country = Country;
            this.Province = Province;
            this.City = City;
        }
        public String getCountry(){return Country;}
        public String getProvince(){return Province;}
        public String getCity(){return City;}
        public String toString(){return Country + ", " + Province + ", " + City;}
}