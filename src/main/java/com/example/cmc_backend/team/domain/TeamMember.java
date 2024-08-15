package com.example.cmc_backend.team.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.cmc_backend.common.entity.BaseEntity;
import com.example.cmc_backend.common.entity.enums.Status;
import com.example.cmc_backend.team.domain.enums.Part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TeamMember")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class TeamMember extends BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teamId", nullable = false)
	private Team team;

	@Column(name = "name")
	private String name;

	@Column(name = "part")
	@Comment("파트")
	@Enumerated(EnumType.STRING)
	private Part part;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@ColumnDefault("ACTIVE")
	private Status status = Status.ACTIVE;
}
