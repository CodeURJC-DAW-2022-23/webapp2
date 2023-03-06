package com.urjc.asociationPlatform.model;

import java.sql.Blob;
import java.sql.Date;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    private String name;
    private Date date;
    private String month;
    @Lob
    @Column( length = 100000 )
    private String description;
    private String location;
    private String asociation;
    private String campus;
    private boolean credits;
    private boolean booking;
    private String startTime;
    private String endTime;
    private String duration;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments = new ArrayList<>();
    @ManyToMany(cascade=CascadeType.ALL)
    private Set<User> likeList = new HashSet<>();
    @ManyToMany(cascade=CascadeType.ALL)
    private Set<User> dislikeList = new HashSet<>();

    @Lob
    private Blob image;

    public Event(){}
    public Event(String name, Date date, String month, String description, String location, String asociation, String campus, boolean credits, boolean booking, String duration, Blob imgUrl, String startTime, String endTime) {
        this.name = name;
        this.date = date;
        this.month = month;
        this.description = description;
        this.location = location;
        this.asociation=asociation;
        this.campus=campus;
        this.credits=credits;
        this.booking=booking;
        this.duration=duration;
        this.image = imgUrl;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setId(Long id){
        this.id=id;
    }

    public boolean isUserInLikes(User user){
        return likeList.contains(user);
    }

    public int getTotalLikes(){
        return likeList.size();
    }

    public boolean addLike(User user){
        return likeList.add(user);
    }

    public boolean removeLike(User user){
        return likeList.remove(user);
    }

    public boolean isUserInDislikes(User user){
        return dislikeList.contains(user);
    }

    public int getTotalDislikes(){
        return dislikeList.size();
    }

    public boolean addDislike(User user){
        return dislikeList.add(user);
    }

    public boolean removeDislike(User user){
        return dislikeList.remove(user);
    }

    public Long getId(long id){
        return this.id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getDateString(){
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getAsociation() {
        return asociation;
    }
    public void setAsociation(String asociation) {
        this.asociation = asociation;
    }
    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }
    public boolean getCredits() {
        return credits;
    }
    public void setCredits(boolean credits) {
        this.credits = credits;
    }
    public String getCreditsString(){
        if(credits){
            return "Si";
        }
        return "No";
    }
    public boolean getBooking() {
        return booking;
    }
    public void setBooking(boolean booking) {
        this.booking = booking;
    }
    public String getBookingString(){
        if(booking){
            return "Si";
        }
        return "No";
    }
    public String getStartTime(){
        return startTime;
    }
    public void setStartTime(String startTime){
        this.startTime=startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public void setEndTime(String endTime){
        this.endTime=endTime;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public Blob getImage() {
        return image;
    }
    public void setImgUrl(Blob image) {
        this.image = image;
    }

    public List<Comment> getComments(){
        return this.comments;
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }
    
    
}