# should really make location an option in Autohelp

cd ../doc
rm www/Scute.idx 
rm www/ScuteMap.xml 
rm www/Scute.hs 
rm www/ScuteIndex.xml 
rm www/ScuteTOC.xml 

java -jar ../lib/javahelp/Autohelp.jar "Scute" ./www
java -classpath ../lib/javahelp/help-indexer.jar -mx64m oracle.help.tools.index.Indexer -l=en_US ./www ./Scute.idx
# -e=utf8 

# should really make this an option in Autohelp
mv Scute.idx www/
mv ScuteMap.xml www/
mv Scute.hs www/
mv ScuteIndex.xml www/  
mv ScuteTOC.xml www/

cd -

# works ok for JavaHelp, jhindexer is in javahelp2 package in Ubuntu 
#jhindexer ./www

# also javahelp
# java -classpath ../lib/javahelp/jhall.jar com.sun.java.help.search.Indexer ./www ./scute.idx


