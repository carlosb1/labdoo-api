#!/bin/sh

PATH_SCRIPT="/persistent/html/sites/all/modules/facebook_stats_like"
#every day try to execute the script
line="0 8 * * * /bin/sh $PATH_SCRIPT/facebook_ranking.sh"
(crontab -l; echo "$line" ) | crontab -
