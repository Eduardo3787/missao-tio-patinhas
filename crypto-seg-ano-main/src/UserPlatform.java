import java.time.LocalDateTime;

// Entidade associativa do vínculo usuário-plataforma. 

public class UserPlatform {

    private Long id;
    private Long userId;
    private Long platformId;
    private User user; // One User para um UserPlatform
    private Platform platform; // One Platform para um UserPlatform
    private LocalDateTime registeredAt;
    private boolean active;

    public UserPlatform() {
    }


    // overload: construtor com User e Platform
    public UserPlatform(Long id, User user, Platform platform) {
        this.id = id;
        this.user = user;
        this.platform = platform;
        this.userId = user != null ? user.getId() : null;
        this.platformId = platform != null ? 1L : null;
        this.registeredAt = LocalDateTime.now();
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.userId = user.getId();
        }
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // @Override toString
    @Override
    public String toString() {
        return "UserPlatform{" +
                "id=" + id +
                ", userId=" + userId +
                ", platformId=" + platformId +
                ", active=" + active +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
