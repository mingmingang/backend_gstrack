package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gs_track_expo_token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Integer idToken;
    @Column(name = "kry_npk")
    private String idKaryawan;
    @Column(name = "token")
    private String token;
    @Column(name = "create_at")
    private LocalDateTime createAt;

    public Token() {}

    public Integer getIdToken() {
        return idToken;
    }

    public void setIdToken(Integer idToken) {
        this.idToken = idToken;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Token(Integer idToken, String idKaryawan, String token, LocalDateTime createAt) {
        this.idToken = idToken;
        this.idKaryawan = idKaryawan;
        this.token = token;
        this.createAt = createAt;
    }
}
