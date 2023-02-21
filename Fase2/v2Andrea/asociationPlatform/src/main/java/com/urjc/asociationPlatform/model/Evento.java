/* 
package com.urjc.asociationplatflorm.Model;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    @OneToOne
    private String name;
    @OneToOne
    private String date;
    @OneToOne
    private String description;
    @OneToOne
    private String image;
    @OneToOne
    private String aso;
    @OneToOne
    private String place;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    private int capacity;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    private int reservations;

    public Evento(String name, String date, String description, String image, String aso, String place, int capacity, int reservations ){
        this.name = name;
        this.date = date;
        this.description = description;
        this.image = image;
        this.aso = aso;
        this.place = place;
        this.capacity = capacity;
        this.reservations = reservations;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getImage(){
        return this.image;
    }
    public void setImage(String image){
        this.image = image;
    }

    public String getAso(){
        return this.aso;
    }
    public void setAso(String aso){
        this.aso = aso;
    }

    public String getPlace(){
        return this.place;
    }
    public void setPlace(String place){
        this.place = place;
    }

    public int getCapacity(){
        return this.capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getReservations(){
        return this.reservations;
    }
    public void setReservations(int reservations){
        this.reservations = reservations;
    }
}*/