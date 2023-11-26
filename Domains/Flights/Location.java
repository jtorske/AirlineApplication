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
        private String ProvDist;
        private String City;

        public Location(String Country, String ProvDist, String City){
            this.Country = Country;
            this.ProvDist = ProvDist;
            this.City = City;
        }
        public Location(){
            this.Country = "";
            this.ProvDist = "";
            this.City = "";
        }
        public void setCountry(String Country){this.Country = Country;}
        public void setProvDist(String ProvDist){this.ProvDist = ProvDist;}
        public void setCity(String City){this.City = City;}
        
        @Override
        public boolean equals(Object obj){
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Location other = (Location) obj;
            return (this.Country.equals(other.Country) && this.ProvDist.equals(other.ProvDist) && this.City.equals(other.City));
        }
        public String getCountry(){return Country;}
        public String getProvDist(){return ProvDist;}
        public String getCity(){return City;}
        public String toString(){return Country + ", " + ProvDist + ", " + City;}
}