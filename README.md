# AirlinesDatabase
 Simple emulation of an airlines database using java on the front-end and oracle at the back-end

Starting template in Oracle:

create table usersLog (
userId number,
firstName varchar2(30),
lastName varchar2(30),
username varchar2(30),
password varchar2(30),
primary key(userId)
);

create table airlines (
airline_id number,
airline_name varchar2(30),
airline_location varchar2(30),
primary key(airline_id)
);

create table flights (
FID number,
toWhere varchar2(50),
airline_id number,
price number,
primary key(fid),
foreign key(airline_id) references airlines(airline_id)
);

create table usersBalance (
userId number,
balance number,
foreign key (userId) references userslog(userid)
);

create table bookings (
bookingId number,
userId number, 
flightId number,
bookedDate DATE,
primary key(bookingId),
foreign key(userId) references userslog(userid),
foreign key(flightid) references flights(FID)
);


insert into airlines values(1, 'Southwest Airlines', 'Chicago');
insert into airlines values(2, 'Spirit', 'Detroit');
insert into airlines values(3, 'American Airlines', 'New York');
commit;

insert into flights values(1, 'Chicago To Miami', 1, 200);
insert into flights values(2, 'Detroit To St.Louis', 2, 300);
insert into flights values(3, 'New York To Baltimore', 3, 250);
commit;




create or replace trigger UpdateBalanceTable 
after insert on userslog
for each row 
begin
insert into usersBalance(userid, balance) values (:new.UserId, 0);
end;
/

create or replace trigger addToBalance
after insert on bookings
for each row 
declare
price number;
begin
select price into price from flights where fid = :new.flightid;
update usersbalance set balance = balance + price where userid = :new.userid;
end;
/




drop table bookings;
drop table flights;
drop table airlines;
drop table usersBalance;
drop table userslog;
