# board
create table board(
	num int not null auto_increment,
    id varchar(10) not null,
    name varchar(10),
    subject varchar(100) not null,
    content text not null,
    regist_day varchar(30),
    ip varchar(20),
    primary key(num)
)default charset=utf8;

INSERT INTO board (id, subject, content, regist_day) VALUES ('user01', '공지사항1', '내용입니다1', '2024-01-01');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user02', '공지사항2', '내용입니다2', '2024-01-02');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user03', '공지사항3', '내용입니다3', '2024-01-03');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user04', '공지사항4', '내용입니다4', '2024-01-04');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user05', '공지사항5', '내용입니다5', '2024-01-05');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user06', '공지사항6', '내용입니다6', '2024-01-06');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user07', '공지사항7', '내용입니다7', '2024-01-07');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user08', '공지사항8', '내용입니다8', '2024-01-08');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user09', '공지사항9', '내용입니다9', '2024-01-09');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user10', '공지사항10', '내용입니다10', '2024-01-10');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user11', '공지사항11', '내용입니다11', '2024-01-11');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user12', '공지사항12', '내용입니다12', '2024-01-12');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user13', '공지사항13', '내용입니다13', '2024-01-13');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user14', '공지사항14', '내용입니다14', '2024-01-14');
INSERT INTO board (id, subject, content, regist_day) VALUES ('user15', '공지사항15', '내용입니다15', '2024-01-15');
