package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.astratech.backend_gstrack.VO.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository("ReimbursementRepository")
public interface ReimbursementRepository extends JpaRepository<Reimbursement, BigInteger> {
    /**
     * Mengambil daftar reimbursement berdasarkan NPK Karyawan dan tahun pengajuan.
     * Menggunakan LEFT JOIN FETCH untuk mengatasi N+1 problem dan LazyInitializationException
     * dengan mengambil data dari relasi dalam satu query.
     *
     * @param npk  NPK karyawan yang mengajukan.
     * @param year Tahun pengajuan.
     * @return List of Reimbursement entities.
     */
    @Query("SELECT r FROM Reimbursement r " +
            "LEFT JOIN FETCH r.orang o " +
            "LEFT JOIN FETCH o.karyawan k " +
            "WHERE k.npk = :npk AND YEAR(r.rbmCreatedDate) = :year " +
            "ORDER BY r.rbmCreatedDate DESC")
    List<Reimbursement> findReimbursementsByNpkAndYear(@Param("npk") String npk, @Param("year") int year);

    @Query("SELECT r FROM Reimbursement r " +
            "WHERE YEAR(r.rbmCreatedDate) = :year " +
            "ORDER BY r.rbmCreatedDate DESC")
    List<Reimbursement> findReimbursementsByYear(@Param("year") int year);

    @Query("SELECT MIN(YEAR(r.rbmCreatedDate)) FROM Reimbursement r")
    Integer findEarliestYear();

    /**
     * Mencari ID maksimum (rbm_id) dalam rentang ID untuk bulan tertentu.
     * Contoh: Untuk Juli 2025 (2507), rentangnya adalah 250700 sampai 250799.
     * @param minId ID minimum untuk bulan itu (cth: 250700)
     * @param maxId ID maksimum untuk bulan itu (cth: 250799)
     * @return ID terbesar yang ditemukan dalam bentuk Optional<BigInteger>
     */
    @Query("SELECT MAX(r.rbmId) FROM Reimbursement r WHERE r.rbmId >= :minId AND r.rbmId <= :maxId")
    Optional<BigInteger> findMaxIdForCurrentMonth(@Param("minId") BigInteger minId, @Param("maxId") BigInteger maxId);

    /**
     * Menghitung jumlah entri reimbursement dalam rentang ID tertentu.
     * Digunakan untuk menemukan urutan pengajuan pada hari yang sama.
     * Contoh: Untuk tanggal 21 Mei 2024 (240521),
     * kita hitung berapa banyak ID yang sudah ada antara 24052101 dan 24052199.
     *
     * @param startRange ID awal rentang (e.g., 24052101)
     * @param endRange ID akhir rentang (e.g., 24052199)
     * @return jumlah entri dalam rentang tersebut.
     */
    @Query("SELECT COUNT(r) FROM Reimbursement r WHERE r.rbmId >= :startRange AND r.rbmId <= :endRange")
    long countByIdInRange(@Param("startRange") BigInteger startRange, @Param("endRange") BigInteger endRange);

    @Query("SELECT r FROM Reimbursement r LEFT JOIN FETCH r.orang WHERE r.karyawan.npk = :npk")
    List<Orang> findOrangByNpk(@Param("npk") String npk);
}
