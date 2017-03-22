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
USE 'hares';

INSERT INTO `hares`.`hare` (`id`,`name`,`color`,`age` ) VALUES (1,'Harry','Gray',3);
INSERT INTO `hares`.`hare` (`id`,`name`,`color`,`age` ) VALUES (2,'Henrietta','White',2);
INSERT INTO `hares`.`hare` (`id`,`name`,`color`,`age` ) VALUES (3,'Henry','Black',9);

INSERT INTO `hares`.`carrot` (`id`,`name`,`owner`, `rival`) VALUES (1,'The big one',1,3);
INSERT INTO `hares`.`carrot` (`id`,`name`,`owner`, `rival`) VALUES (2,'Orange',1,2);
INSERT INTO `hares`.`carrot` (`id`,`name`,`owner`, `rival`) VALUES (3,'The small',2,null);
INSERT INTO `hares`.`carrot` (`id`,`name`,`owner`, `rival`) VALUES (4,'The old and rotten',3,null);

INSERT INTO `hares`.`human` (`id`,`name`) VALUES (1,'Alice');
INSERT INTO `hares`.`human` (`id`,`name`) VALUES (2,'Bob');

INSERT INTO `hares`.`friend` (`hare`,`human`) VALUES (1,1);
INSERT INTO `hares`.`friend` (`hare`,`human`) VALUES (2,1);
INSERT INTO `hares`.`friend` (`hare`,`human`) VALUES (3,1);
INSERT INTO `hares`.`friend` (`hare`,`human`) VALUES (3,2);

commit;