// Ez School - Main JavaScript
// ===================================

document.addEventListener('DOMContentLoaded', function() {
    
    // ===================================
    // Navbar Scroll Effect
    // ===================================
    const navbar = document.querySelector('.navbar');
    let lastScroll = 0;
    
    window.addEventListener('scroll', () => {
        const currentScroll = window.pageYOffset;
        
        if (currentScroll > 100) {
            navbar.style.boxShadow = '0 4px 12px rgba(0, 0, 0, 0.1)';
        } else {
            navbar.style.boxShadow = '0 2px 8px rgba(0, 0, 0, 0.05)';
        }
        
        lastScroll = currentScroll;
    });

    // ===================================
    // Mobile Menu Toggle
    // ===================================
    const mobileMenuBtn = document.querySelector('.mobile-menu-btn');
    const navLinks = document.querySelector('.nav-links');
    
    if (mobileMenuBtn) {
        mobileMenuBtn.addEventListener('click', () => {
            navLinks.classList.toggle('active');
            mobileMenuBtn.classList.toggle('active');
            
            // Animate hamburger icon
            const spans = mobileMenuBtn.querySelectorAll('span');
            if (mobileMenuBtn.classList.contains('active')) {
                spans[0].style.transform = 'rotate(45deg) translate(5px, 5px)';
                spans[1].style.opacity = '0';
                spans[2].style.transform = 'rotate(-45deg) translate(7px, -6px)';
            } else {
                spans[0].style.transform = 'none';
                spans[1].style.opacity = '1';
                spans[2].style.transform = 'none';
            }
        });
    }

    // ===================================
    // Search Functionality
    // ===================================
    const searchInput = document.querySelector('.search-input');
    const searchBtn = document.querySelector('.search-btn');
    const popularTags = document.querySelectorAll('.tag');
    
    if (searchBtn) {
        searchBtn.addEventListener('click', () => {
            const query = searchInput.value.trim();
            if (query) {
                // Redirect to courses page with search query
                window.location.href = `/courses?search=${encodeURIComponent(query)}`;
            }
        });
    }
    
    if (searchInput) {
        searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                const query = searchInput.value.trim();
                if (query) {
                    window.location.href = `/courses?search=${encodeURIComponent(query)}`;
                }
            }
        });
    }
    
    // Popular tags click
    popularTags.forEach(tag => {
        tag.addEventListener('click', (e) => {
            e.preventDefault();
            const query = tag.textContent.trim();
            searchInput.value = query;
            window.location.href = `/courses?search=${encodeURIComponent(query)}`;
        });
    });

    // ===================================
    // Smooth Scroll Animation
    // ===================================
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in-up');
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);
    
    // Observe elements for animation
    const animatedElements = document.querySelectorAll('.step-card, .mentor-card, .price-card');
    animatedElements.forEach(el => observer.observe(el));

    // ===================================
    // Stats Counter Animation
    // ===================================
    const stats = document.querySelectorAll('.stat-number');
    let hasAnimated = false;
    
    const animateCounter = (element, target, suffix = '') => {
        const duration = 2000;
        const start = 0;
        const increment = target / (duration / 16);
        let current = start;
        
        const timer = setInterval(() => {
            current += increment;
            if (current >= target) {
                element.textContent = target + suffix;
                clearInterval(timer);
            } else {
                element.textContent = Math.floor(current) + suffix;
            }
        }, 16);
    };
    
    const statsObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting && !hasAnimated) {
                hasAnimated = true;
                stats.forEach(stat => {
                    const text = stat.textContent;
                    let target, suffix = '';
                    
                    if (text.includes('K')) {
                        target = parseInt(text);
                        suffix = 'K+';
                    } else if (text.includes('%')) {
                        target = parseInt(text);
                        suffix = '%';
                    } else {
                        target = parseInt(text);
                        suffix = '+';
                    }
                    
                    animateCounter(stat, target, suffix);
                });
            }
        });
    }, { threshold: 0.5 });
    
    const heroStats = document.querySelector('.hero-stats');
    if (heroStats) {
        statsObserver.observe(heroStats);
    }

    // ===================================
    // Mentor Card Interactions
    // ===================================
    const mentorCards = document.querySelectorAll('.mentor-card');
    
    mentorCards.forEach(card => {
        card.addEventListener('mouseenter', () => {
            const avatar = card.querySelector('.mentor-avatar img');
            if (avatar) {
                avatar.style.transform = 'scale(1.05)';
                avatar.style.transition = 'transform 0.3s ease';
            }
        });
        
        card.addEventListener('mouseleave', () => {
            const avatar = card.querySelector('.mentor-avatar img');
            if (avatar) {
                avatar.style.transform = 'scale(1)';
            }
        });
    });

    // ===================================
    // Price Card Selection
    // ===================================
    const priceCards = document.querySelectorAll('.price-card');
    
    priceCards.forEach(card => {
        const btn = card.querySelector('.btn');
        if (btn) {
            btn.addEventListener('click', (e) => {
                e.preventDefault();
                
                // Visual feedback
                card.style.transform = 'scale(0.98)';
                setTimeout(() => {
                    card.style.transform = '';
                }, 200);
                
                // In a real app, this would redirect to checkout
                const planName = card.querySelector('.price-title').textContent;
                console.log(`Selected plan: ${planName}`);
                
                // Simulate redirect
                // window.location.href = `/checkout?plan=${encodeURIComponent(planName)}`;
            });
        }
    });

    // ===================================
    // Free Lesson Join Buttons
    // ===================================
    const freeLessonBtns = document.querySelectorAll('.mentor-card .btn-small');
    
    freeLessonBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            
            // Get lesson info
            const card = btn.closest('.mentor-card');
            const mentorName = card.querySelector('.mentor-name').textContent;
            const lessonTitle = card.querySelector('.lesson-title').textContent;
            
            // Show confirmation message (in production, this would open a modal or redirect)
            showNotification(`Joining ${lessonTitle} with ${mentorName}!`);
            
            // In a real app, this would redirect to the lesson page
            // window.location.href = `/lesson/join?mentor=${encodeURIComponent(mentorName)}`;
        });
    });

    // ===================================
    // Notification System
    // ===================================
    function showNotification(message) {
        const notification = document.createElement('div');
        notification.style.cssText = `
            position: fixed;
            top: 100px;
            right: 24px;
            background: linear-gradient(135deg, #7C3AED, #3B82F6);
            color: white;
            padding: 16px 24px;
            border-radius: 12px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            z-index: 10000;
            font-weight: 600;
            animation: slideInRight 0.3s ease;
        `;
        notification.textContent = message;
        document.body.appendChild(notification);
        
        setTimeout(() => {
            notification.style.animation = 'slideOutRight 0.3s ease';
            setTimeout(() => {
                document.body.removeChild(notification);
            }, 300);
        }, 3000);
    }

    // ===================================
    // Dynamic Comparison Table
    // ===================================
    const comparisonRows = document.querySelectorAll('.comparison-row');
    
    comparisonRows.forEach((row, index) => {
        row.style.animationDelay = `${index * 0.1}s`;
    });

    // ===================================
    // Add CSS animations dynamically
    // ===================================
    const style = document.createElement('style');
    style.textContent = `
        @keyframes slideInRight {
            from {
                transform: translateX(400px);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
        
        @keyframes slideOutRight {
            from {
                transform: translateX(0);
                opacity: 1;
            }
            to {
                transform: translateX(400px);
                opacity: 0;
            }
        }
        
        @media (max-width: 768px) {
            .nav-links.active {
                display: flex;
                flex-direction: column;
                position: absolute;
                top: 100%;
                left: 0;
                right: 0;
                background: white;
                padding: 24px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                gap: 16px;
            }
        }
    `;
    document.head.appendChild(style);

    // ===================================
    // Lazy Loading Images
    // ===================================
    const images = document.querySelectorAll('img[src]:not(.logo-img):not(.footer-logo-img):not(.mentor-avatar img)');
    
    const imageObserver = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const img = entry.target;
                img.style.opacity = '0';
                img.style.transition = 'opacity 0.3s ease';
                
                img.onload = () => {
                    img.style.opacity = '1';
                };
                
                observer.unobserve(img);
            }
        });
    });
    
    images.forEach(img => imageObserver.observe(img));

    // ===================================
    // Easter Egg: Konami Code
    // ===================================
    let konamiCode = [];
    const konamiPattern = ['ArrowUp', 'ArrowUp', 'ArrowDown', 'ArrowDown', 'ArrowLeft', 'ArrowRight', 'ArrowLeft', 'ArrowRight', 'b', 'a'];
    
    document.addEventListener('keydown', (e) => {
        konamiCode.push(e.key);
        konamiCode = konamiCode.slice(-10);
        
        if (konamiCode.join(',') === konamiPattern.join(',')) {
            showNotification('🎉 You found the secret! Learning is fun!');
            document.body.style.animation = 'rainbow 3s ease infinite';
        }
    });

    // ===================================
    // Performance: Debounce scroll events
    // ===================================
    function debounce(func, wait) {
        let timeout;
        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    }

    // Apply debounce to scroll event if needed
    const debouncedScroll = debounce(() => {
        // Additional scroll-based functionality can go here
    }, 100);
    
    window.addEventListener('scroll', debouncedScroll);

    // ===================================
    // Console Welcome Message
    // ===================================
    console.log('%cWelcome to Ez School! 🎓', 'font-size: 24px; font-weight: bold; color: #7C3AED;');
    console.log('%cSkills made easy. Built with 💜', 'font-size: 14px; color: #6B7280;');
    console.log('%cInterested in how we built this? Check out our courses! 😄', 'font-size: 12px; color: #3B82F6;');
});
