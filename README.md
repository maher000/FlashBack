# FlashBack

**FlashBack** is a community-centric photo-sharing application developed for Android using **Kotlin**. It enables users to capture and share daily photos within interest-based communities, fostering authentic connections and personalized content discovery.

FlashBack allows users to join communities aligned with their interests—such as dog lovers, food enthusiasts, or travelers—and share daily photos. By selecting at least three categories upon account creation, users receive a tailored feed showcasing content that resonates with their passions.

---

## 1. Product Vision

- **Community-Driven Content**  
  Bring people together around shared interests—dog lovers, foodies, travelers, etc.—by prompting them to capture and share authentic photos daily.

- **Personalized Discovery**  
  Each user chooses at least three categories of interest at signup, ensuring the feed is always relevant, meaningful, and engaging.

- **Inclusive & Effortless Sharing**  
  Simplify the user experience so that sharing a quick, daily snapshot becomes an easy habit—strengthening each niche community and boosting overall engagement.

---

## 2. Product Job to Be Done

- **Authentic Connection**  
  Encourage users to share “real-life” moments tied to their interests (e.g., a dog owner’s morning walk, a cooking enthusiast’s homemade meal). This fosters genuine connections rather than superficial “like” exchanges.

- **Tailored Discovery & Inspiration**  
  Show users a continuous feed of content tailored to the categories they selected, helping them explore new ideas, trends, and communities within their favorite niches.

- **Daily Engagement**  
  By nudging users to capture photos daily, FlashBack develops a consistent user habit. This keeps content fresh, encourages repeat visits, and stimulates ongoing community participation.

- **Gamification & Challenges**  
  Introduce daily and weekly challenges (e.g., theme-based prompts, photo streaks) and reward consistent participation with badges or trophies. This injects fun into the experience and encourages users to return regularly.

---

## 3. How We’ll Reach 1M Daily Active Users (DAU)

### **Niche Community Virality**
- Each interest-based group (e.g., dog lovers) is self-propagating. Passionate members naturally invite friends or fellow hobbyists to join.
- Influencers or local clubs within these categories can quickly rally their followers, leading to organic growth within each niche.
- Ads on social media to promote the application with well-designed videos or photos.

### **Targeted Onboarding**
- Simple signup that prompts users to pick three favorite categories right away. This ensures immediate personalized content and a stronger hook for daily returns.

### **Engagement Loops & Habit Formation**
- A “daily capture” notification with streaks or badges incentivizes users to share every day, making FlashBack part of their routine.

### **Cross-Promotion & Partnerships**
- Collaborations with relevant brands, pet shelters, travel agencies, or cooking forums can funnel already engaged audiences into FlashBack’s tailored groups.
- Strategic influencer campaigns, especially within highly active niches, spur rapid adoption.

### **Scalable Platform & Monetization**
- As each interest group grows, the overall platform benefits from diverse user bases, paving the way to 1M DAU through combined network effects.
- Monetization channels (e.g., sponsored posts from pet-friendly hotels for dog lovers) will fuel sustainable growth and reinvestment into user acquisition.

---

## 4. Features

1. **Feed**  
   Displays a list of user photos, including a short description.

2. **Image Capture**  
   Open the camera to take a photo.

3. **Photo Share**  
   Upload the taken photo with the possibility to add a description.

4. **Profile**  
   The user profile where they can view all their shared photos.

5. **Bottom Navigation Bar**  
   Provides easy navigation between the feed, camera, and profile screens.

---

## 5. Technologies Used

1. **Kotlin**: The primary programming language for the app.
2. **Dependency Injection**: Implemented using Koin for modular and testable code.
3. **Jetpack Compose**: Used for building the user interface in a modern and declarative style.
4. **Coil**: For asynchronous image loading.
5. **Coroutines**: For managing asynchronous tasks, including API calls and data processing.
6. **MVVM Architecture**: Ensures a clean separation of concerns using ViewModel and Repository patterns.

---

## 6. Technical Enhancements

1. Replace mocks with data from a real backend.
2. Add pagination when loading data.
3. Improve navigation between screens.
4. Implement offline support for viewing photos.
5. Add unit tests.

   ---
   
## 7. Setup and Installation

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/maher000/FlashBack.git
   ```

2. **Open the project in Android Studio.**  
  
3. **Build and run the project on an emulator or a physical device.**  
   
