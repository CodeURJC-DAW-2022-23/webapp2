package com.urjc.asociationPlatform.model;

import java.sql.Blob;
import java.sql.Date;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.urjc.asociationPlatform.service.AsociationService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
    @ManyToOne ()
    //@JsonIgnore
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Asociation asociation;
    private String campus;
    private boolean credits;
    private boolean booking;
    private String startTime;
    private String endTime;
    private String duration;
    //@JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    //@JsonIgnore
    @ManyToMany()
    private Set<User> likeList = new HashSet<>();
    //@JsonIgnore
    @ManyToMany()
    private Set<User> dislikeList = new HashSet<>();

    //@JsonIgnore
    @Lob
    private Blob image;

    public Event(){}
    public Event(String name, Date date, String month, String description, String location, Asociation asociation, String campus, boolean credits, boolean booking, String duration, Blob imgUrl, String startTime, String endTime) {
        this.name = name;
        this.date = date;
        this.month = month;
        this.description = description;
        this.location = location;
        this.campus=campus;
        this.credits=credits;
        this.booking=booking;
        this.duration=duration;
        this.image = imgUrl;
        this.startTime = startTime;
        this.endTime = endTime;

        this.asociation=asociation;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
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

    public Long getId(){
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
    public Asociation getAsociation() {
        return asociation;
    }
    public String getAsociationName(){
        return asociation.getName();
    }
    public void setAsociation(Asociation asociation) {
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

    public boolean getBooking() {
        return booking;
    }
    public void setBooking(boolean booking) {
        this.booking = booking;
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
        comment.setEvent(this);
        this.comments.add(comment);
    }

    public void removeComment(Comment comment){
        comment.setEvent(null);
        this.comments.remove(comment);
    }
    
    
    public void clearComments(){
        for (Comment comment : comments) {
            removeComment(comment);
        }
    }
    public void clear() {
        likeList.clear();
        dislikeList.clear();
    }
    public List<Comment> deleteUserReferences(User user) {
        if(likeList.contains(user))
            removeLike(user);
        if(dislikeList.contains(user))
            removeDislike(user);
        List<Comment> output = new ArrayList<>();
        for(Comment comment:comments){
            if(comment.isUserInFavorites(user)){
                comment.removeFavorites(user);
                output.add(comment);
            } 
        }
        return output;
    }
}
