drop database if exists SneakersDB;

create database if not exists SneakersDB;

use SneakersDB;


create table `users`(
`id` int primary key auto_increment,
`firstname` varchar(100) not null,
`lastname` varchar(100) not null,
`dateofbirth` date not null,
`phone` varchar(20) not null,
`email` varchar(100) not null,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null);

create table `roles`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table `users_roles`(
`user_id` int not null,
`role_id` int not null,
primary key(`user_id`,`role_id`),
foreign key (`user_id`) references `users`(`id`),
foreign key (`role_id`) references `roles`(`id`));

create table brands (
	id					int					primary key auto_increment,
	name 			varchar(100)			not null
);

create table primarycolors (
	id 					int					primary key auto_increment,
	name				varchar(50)			not null
);

create table secondarycolors (
	id 					int					primary key auto_increment,
	name				varchar(50)			not null
);

create table sizes (
	shoesize			int					primary key
);

create table models (
	id					int					primary key auto_increment,
	name				varchar(100)		not null,
	brandid				int					not null,
	releaseyear			int					not null,
	primarycolorid		int					not null,
    secondarycolorid	int					null,
	msrp_price			decimal(8,2)		not null,
		foreign key fk_brands_models (brandid) references brands (id),
		foreign key fk_primarycolors_models (primarycolorid) references primarycolors (id),
        foreign key fk_secondarycolors_models (secondarycolorid) references secondarycolors (id)
);

create table types (
	id					int					primary key auto_increment,
    name				varchar(50)			not null
    );
    
create table models_types (
	modelid				int					not null,
    typeid				int					not null,
		foreign key fk_models_models_types (modelid) references models (id),
        foreign key fk_types_models_types (typeid) references types (id)
);
    
create table status (
	id					int					primary key auto_increment,
    name				varchar(50)			not null
);

create table shoeconditions (
	id					int					primary key auto_increment,
    name				varchar(50)			not null
);    
    

create table listings (
	id					int					primary key auto_increment,
	statusid			int					not null,
	modelid				int					not null,
	shoeconditionid		int					not null,
	shoesizeid			int					not null,			
	description			varchar(1000)		not null,
    buynowprice			decimal(8,2)		not null,
    minstartingprice	decimal(8,2)		not null,
	listdate			date				not null,
	enddate				date				null, -- this is the listing end date (i.e. the deadline for bids to be entered)
	sellerid			int					not null,
	photo				varchar(1000)		null,
    typeid				int					not null,
		foreign key fk_status_listings (statusid) references status (id),
		foreign key fk_models_listings (modelid) references models (id),
        foreign key fk_shoeconditions_listings (shoeconditionid) references shoeconditions (id),
        foreign key fk_sizes_listings (shoesizeid) references sizes (shoesize),
		foreign key fk_users_listings (sellerid) references users (id),
        foreign key fk_types_listings (typeid) references types (id)
);


create table purchases (
	id					int					primary key auto_increment,
    listingid			int					not null,
    saleprice			decimal(8,2)		not null,
    sellerid			int					not null,
    buyerid				int					not null,
    datesold			date				not null,
		foreign key fk_listings_purchases (listingid) references listings (id),
        foreign key fk_suser_purchases (sellerid) references users (id),
        foreign key fk_buser_purchases (buyerid) references users (id)
);

create table bids (
	id					int					primary key auto_increment,
	listingid			int					not null,
    buyerid				int					not null,
    bidprice			decimal(8,2)		not null,
    date				date				not null,
		foreign key fk_listings_bids (listingid) references listings (id),
        foreign key fk_buser_bids (buyerid) references users (id)
);


