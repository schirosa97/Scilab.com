drop database if exists  scilab_db ;
create database scilab_db;
use scilab_db;

create table utente(
				Id_user int not null,
				Nome varchar (20) not null,
				Cognome varchar(20) not null,
				username varchar(20) not null,
                passwordhash varchar(50) not null,
                email varchar(25) not null,
                Immagine varchar(40) default null,
                primary key(username,passwordhash),
                unique key(Id_user)
                );


create table admin(
				id_admn int not null,
				admin_user varchar(20) not null,
                adminpasshash varchar(50) not null,
                privilage boolean default true,
                primary key(admin_user,adminpasshash)
				);

create table category(
                         Id_category int not null AUTO_INCREMENT,
                         category_name varchar(25) not null,
                         cat_description varchar(200) not null,
                         img_category varchar(25) not null,
                         primary key(Id_category)
                        );

create table product(
				Id_product int not null,
                name_product varchar(40) not null,
                short_descripton varchar(60),
                long_description varchar(250),
                width_prod double,
                height_prod double,
                price double not null,
                bookmarked boolean not null default false,
                qty_product int not null,
                primary key(Id_product)
                );

create table shipping_cart(
                              Id_sc int not null,
                              Id_usr int not null,
                              Id_prd int not null ,
                              Id_cat int not null ,
                              Total_price double not null,
                              qty_product int not null,
                              primary key(Id_sc,Id_prd,Id_usr,Id_cat),
                              foreign key(Id_usr) references Utente(Id_user),
                              foreign key (Id_prd) references Product(Id_product),
                              foreign key (Id_cat) references Category(Id_category)
                              );

create table preference(
                    Id_client int not null,
                    Id_prod int not null,
                    primary key (Id_client,Id_prod),
                    foreign key (Id_client) references Utente(Id_user),
                    foreign key (Id_prod) references Product(Id_product)
                    );

create table orders(
				Id_orders int not null,
                Id_us int not null,
                orders_date date not null,
                orders_name varchar(20) not null,
                status_order varchar(10) not null,
                tot_price double not null,
                primary key(Id_orders)
                );

create table shipment(
				Id_ship int not null,
                Id_orderi int not null,
                Id_usr int not null,
                name_ship varchar(15) not null,
                Type_ship varchar(10) not null,
                time_ship varchar(10) not null,
                primary key(Id_ship,Id_orderi,Id_usr),
                foreign key(Id_orderi) references Orders(Id_orders),
                foreign key (Id_usr) references Utente(Id_user)
                );

create table payment(
				Id_payment int not null,
                Id_ord int not null,
                name_pay varchar(20) not null,
                type_pay varchar(20) not null,
                date_pay date not null,
                total_pay double not null,
                primary key(Id_payment),
                foreign key(Id_ord) references Orders(Id_orders)
                );

create table categoryprod(
					Id_prod int not null,
                    Id_Cat int not null,
                    primary key(Id_prod,Id_Cat),
                    foreign key(Id_prod) references Product(Id_product),
                    foreign key(Id_Cat) references Category(Id_category)
                    );
create table orderprod (
                           Id_order int not null,
                           Id_prod int not null,
                           primary key(Id_order,Id_prod),
                           foreign key(Id_order) references Orders(Id_orders),
                           foreign key(Id_prod) references Product(Id_product)
);

INSERT INTO product value (15,'Alambicco chimico','alambicco chimico proviente dalla russia',null,0,0,50.75,default,4);
INSERT INTO category value (04,'Strumenti fisici','Sono elencati tutti gli strumenti fisici piu utilizzati','PhyscisIcon');
INSERT INTO category value (01,'Strumenti chimici','Sono elencati tutti gli strumenti chimici piu utilizzati','ChimestryIcon');
INSERT INTO category value (03,'Strumenti matematici','Sono elencati tutti gli strumenti matematici piu utilizzati','MathIcon');
INSERT INTO category value (02,'Strumenti informatici','Sono elencati tutti gli strumenti informatici piu utilizzati','ComputerScienceIcon');
INSERT INTO categoryprod value (15, 01);
                
                
                
                
                
					

                
                
                
				

	
            
            
            
                
				
