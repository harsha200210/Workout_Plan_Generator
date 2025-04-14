# 🏋️‍♂️ Workout Plan Generator

The **Workout Plan Generator** is a full-featured fitness planning application designed to create personalized workout routines based on user preferences and physical attributes. It includes progress tracking, AI-powered recommendations, and admin-level control over fitness content.

---

## 🚀 Features

### 👤 User Features

- 🔐 **Google Login** via Firebase Authentication
- 📅 Generate personalized workout plans based on:
  - Body type
  - Target body type
  - Current weight
  - Target weight
  - Workout duration
- 🤖 **AI-selected exercises** (Gemini AI)
- 🎥 Each plan includes:
  - List of exercises
  - YouTube tutorial links
  - Option to download the plan
- 📊 **Monthly tracking**:
  - Record weight
  - BMI
- 🍱 View personal meal plans
- 🏋️ Add custom workout places and trainers
- 🔁 View previous and current workout schedules
- 📆 After the initial plan (3 days), choose next plan type (2-day or 3-day plan)

---

### 🛠️ Admin Features

- 📊 **Admin Dashboard**:
  - Total users
  - Total exercises
  - Total plans
- 📝 Manage exercises:
  - Add new exercises
  - Update existing exercises
  - Delete exercises
  - View all exercises
- 🔒 Change admin password

---

### 🔐 Security & Automation

- ✅ **JWT Security** for API protection
- 📧 **Automated Monthly Email Notifications**:
  - Sent on the 1st of each month
  - Reminds users to check and update weight/BMI

---

## 🛠️ Tech Stack

- **Frontend:** HTML/CSS, JavaScript (boostrap)
- **Backend:** Spring Boot (java)
- **Authentication:** Firebase Google Login
- **Database:** MySQL
- **AI Integration:** Gemini AI (for exercise selection)
- **Security:** JWT Authentication
- **Email Service:** SMTP (for monthly notifications)

---

## 📸 Screenshots

### Main Page
![Main Page](ss/Screenshot%202025-04-14%20124453.png)

### Login Page
![Login Page](ss/Screenshot%202025-04-14%20124516.png)

### Sign Up Page
![Sign Up Page](ss/Screenshot%202025-04-14%20124528.png)

### Weight Count Section
![Weight Count Section](ss/Screenshot%202025-04-14%20124602.png)

### BMI Count Section
![BMI Count Section](ss/Screenshot%202025-04-14%20124614.png)

### Meal Plan Page
![Meal Plan Page](ss/Screenshot%202025-04-14%20124638.png)

### Workout Places Page
![Workout Places Page](ss/Screenshot%202025-04-14%20124651.png)

### Trainers Page
![Trainers Page](ss/Screenshot%202025-04-14%20124702.png)

### Schedules Page
![Schedules Page](ss/Screenshot%202025-04-14%20124714.png)

### Generate Plan Page
![Generate Plan Page](ss/Screenshot%202025-04-14%20124741.png)

---

## 📽️ Demonstration Video

<a href="https://youtu.be/HL8e8BSeWxI">
  <img src="ss/Red%20Bold%20Finance%20YouTube%20Thumbnail.png" width="200" alt="Watch the demo">
</a>


## 📦 Installation

1. Clone the repository  
   ```bash
   git clone https://github.com/harsha200210/Workout_Plan_Generator.git
   cd workout-plan-generator
