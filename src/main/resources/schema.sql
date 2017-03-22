-- /**
--  *
--  * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
--  *
--  * Licensed under the Apache License, Version 2.0 (the "License"); You may not
--  * use this file except in compliance with the License. You may obtain a copy of
--  * the License at:
--  *
--  * http://www.apache.org/licenses/LICENSE-2.0
--  *
--  * Unless required by applicable law or agreed to in writing, software
--  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
--  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
--  * License for the specific language governing permissions and limitations under
--  * the License.
--  */
CREATE DATABASE 'hares';
USE 'hares';

CREATE TABLE `hares`.`hare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS `hares`.`carrot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `owner` int(11) NOT NULL,
  `rival` int(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS `hares`.`human` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=100;

CREATE TABLE `hares`.`friend` (
  `hare` int(11) NOT NULL,
  `human` int(11) NOT NULL,
  PRIMARY KEY (`hare`, `human`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `hares`.`carrot`
  ADD CONSTRAINT `carrot_owner_to_hare_id` FOREIGN KEY (`owner`) REFERENCES `hare` (`id`);
ALTER TABLE `hares`.`carrot`
  ADD CONSTRAINT `carrot_rival_to_hare_id` FOREIGN KEY (`rival`) REFERENCES `hare` (`id`);

ALTER TABLE `hares`.`friend`
  ADD CONSTRAINT `friend_hare_to_hare_id` FOREIGN KEY (`hare`) REFERENCES `hare` (`id`);
ALTER TABLE `hares`.`friend`
  ADD CONSTRAINT `friend_human_to_human_id` FOREIGN KEY (`human`) REFERENCES `human` (`id`);

commit;