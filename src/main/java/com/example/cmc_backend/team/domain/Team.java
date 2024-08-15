package com.example.cmc_backend.team.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.cmc_backend.common.entity.BaseEntity;
import com.example.cmc_backend.common.entity.enums.Status;
import com.example.cmc_backend.team.domain.enums.TeamClassification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Team")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Team extends BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "boothLocation")
	@Comment("부스 위치")
	private String boothLocation;

	@Column(name = "logoUrl")
	@Comment("로고 URL")
	private String logoUrl;

	@Column(name = "projectImgUrl")
	@Comment("프로젝트 이미지 URL")
	private String projectImgUrl;

	@Column(name = "teamClassification")
	@Comment("팀 분류")
	@Enumerated(EnumType.STRING)
	private TeamClassification classification;

	@Column(name= "temaName")
	@Comment("팀명")
	private String teamName;

	@Column(name = "serviceIntroduction")
	@Comment("서비스 소개")
	private String serviceIntroduction;

	@Column(name = "explanation")
	@Comment("설명")
	private String explanation;

	@Column(name = "mainFunctions")
	@Comment("주요기능")
	private String mainFunctions;

	@Column(name = "appStoreUrl")
	@Comment("앱스토어 URL")
	private String appStoreUrl;

	@Column(name = "playStoreUrl")
	@Comment("플레이스토어 URL")
	private String playStoreUrl;

	@Column(name = "code")
	@Comment("코드")
	private String code;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "teamId")
	@Comment("팀원")
	@BatchSize(size = 20)
	private List<TeamMember> teamMembers;

	@Column(name = "status")
	@ColumnDefault("ACTIVE")
	private Status status = Status.ACTIVE;

}

