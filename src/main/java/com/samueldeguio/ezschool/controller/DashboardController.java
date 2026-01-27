package com.samueldeguio.ezschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Random random = new Random();
        
        // Randomized Course Stats
        model.addAttribute("coursesEnrolled", 3 + random.nextInt(5));
        model.addAttribute("coursesCompleted", 1 + random.nextInt(3));
        model.addAttribute("totalWatchTime", 12 + random.nextInt(36));
        model.addAttribute("currentStreak", 3 + random.nextInt(15));
        model.addAttribute("completionRate", 75 + random.nextInt(20));
        
        // Achievements
        List<Map<String, Object>> achievements = new ArrayList<>();
        achievements.add(createAchievement("First Step", "Completed your first course", true, "🎓"));
        achievements.add(createAchievement("Week Warrior", "7-day learning streak", random.nextBoolean(), "🔥"));
        achievements.add(createAchievement("Social Learner", "Attended 5 live classes", random.nextBoolean(), "👥"));
        achievements.add(createAchievement("Night Owl", "Study session after midnight", random.nextBoolean(), "🦉"));
        achievements.add(createAchievement("Early Bird", "Lesson completed before 8 AM", random.nextBoolean(), "🌅"));
        achievements.add(createAchievement("Perfectionist", "100% on a quiz", random.nextBoolean(), "⭐"));
        model.addAttribute("achievements", achievements);
        
        // Live Classes
        List<Map<String, Object>> liveClasses = new ArrayList<>();
        liveClasses.add(createLiveClass("React Hooks Deep Dive", "Sarah Chen", "Today", "3:00 PM", "live", "vibrant-purple"));
        liveClasses.add(createLiveClass("Python Data Analysis", "Marcus Johnson", "Tomorrow", "5:30 PM", "upcoming", "electric-blue"));
        liveClasses.add(createLiveClass("UI/UX Design Principles", "Emma Rodriguez", "Wed", "2:00 PM", "upcoming", "soft-teal"));
        liveClasses.add(createLiveClass("Leadership Workshop", "David Kim", "Thu", "6:00 PM", "upcoming", "coral-orange"));
        model.addAttribute("liveClasses", liveClasses);
        
        // Calendar Events (Next 2 weeks)
        List<Map<String, Object>> calendarEvents = new ArrayList<>();
        LocalDate today = LocalDate.now();
        
        // Add some sample lessons
        calendarEvents.add(createCalendarEvent(today, "React Hooks Deep Dive", "3:00 PM", "vibrant-purple"));
        calendarEvents.add(createCalendarEvent(today.plusDays(1), "Python Data Analysis", "5:30 PM", "electric-blue"));
        calendarEvents.add(createCalendarEvent(today.plusDays(2), "UI/UX Design Principles", "2:00 PM", "soft-teal"));
        calendarEvents.add(createCalendarEvent(today.plusDays(3), "Leadership Workshop", "6:00 PM", "coral-orange"));
        calendarEvents.add(createCalendarEvent(today.plusDays(5), "Advanced JavaScript", "4:00 PM", "vibrant-purple"));
        calendarEvents.add(createCalendarEvent(today.plusDays(7), "Data Visualization", "3:30 PM", "electric-blue"));
        calendarEvents.add(createCalendarEvent(today.plusDays(9), "Public Speaking Mastery", "7:00 PM", "coral-orange"));
        calendarEvents.add(createCalendarEvent(today.plusDays(12), "Team Collaboration", "5:00 PM", "soft-teal"));
        
        model.addAttribute("calendarEvents", calendarEvents);
        model.addAttribute("currentMonth", today.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        
        // Current Courses Progress
        List<Map<String, Object>> currentCourses = new ArrayList<>();
        currentCourses.add(createCourseProgress("Full-Stack Web Development", "Sarah Chen", 65 + random.nextInt(20), 24, 36));
        currentCourses.add(createCourseProgress("Data Science Fundamentals", "Marcus Johnson", 40 + random.nextInt(20), 12, 28));
        currentCourses.add(createCourseProgress("UX Design Mastery", "Emma Rodriguez", 80 + random.nextInt(15), 20, 24));
        model.addAttribute("currentCourses", currentCourses);
        
        return "pages/dashboard";
    }
    
    private Map<String, Object> createAchievement(String title, String description, boolean unlocked, String icon) {
        Map<String, Object> achievement = new HashMap<>();
        achievement.put("title", title);
        achievement.put("description", description);
        achievement.put("unlocked", unlocked);
        achievement.put("icon", icon);
        return achievement;
    }
    
    private Map<String, Object> createLiveClass(String title, String mentor, String day, String time, String status, String color) {
        Map<String, Object> liveClass = new HashMap<>();
        liveClass.put("title", title);
        liveClass.put("mentor", mentor);
        liveClass.put("day", day);
        liveClass.put("time", time);
        liveClass.put("status", status);
        liveClass.put("color", color);
        return liveClass;
    }
    
    private Map<String, Object> createCalendarEvent(LocalDate date, String title, String time, String color) {
        Map<String, Object> event = new HashMap<>();
        event.put("date", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        event.put("day", date.getDayOfMonth());
        event.put("dayName", date.format(DateTimeFormatter.ofPattern("EEE")));
        event.put("title", title);
        event.put("time", time);
        event.put("color", color);
        return event;
    }
    
    private Map<String, Object> createCourseProgress(String title, String mentor, int progress, int lessonsCompleted, int totalLessons) {
        Map<String, Object> course = new HashMap<>();
        course.put("title", title);
        course.put("mentor", mentor);
        course.put("progress", progress);
        course.put("lessonsCompleted", lessonsCompleted);
        course.put("totalLessons", totalLessons);
        return course;
    }
}
