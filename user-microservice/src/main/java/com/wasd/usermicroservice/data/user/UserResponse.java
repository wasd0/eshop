package com.wasd.usermicroservice.data.user;

import java.time.LocalDateTime;

public record UserResponse(Long id, String username, String email, LocalDateTime registeredAt) {
}
