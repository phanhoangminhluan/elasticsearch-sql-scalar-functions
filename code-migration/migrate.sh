
SOURCE=/Users/phanhoangminhluan/Projects/es1/elasticsearch
TARGET=/Users/phanhoangminhluan/Projects/elasticsearch-scalar-functions/src/main/java/org/elasticsearch/xpack

rm -r "${TARGET}"

mkdir "${TARGET}"
mkdir "${TARGET}/ql"
mkdir "${TARGET}/sql"

scp -r "${SOURCE}/x-pack/plugin/ql/src/main/java/org/elasticsearch/xpack/ql/" "${TARGET}/ql/"
echo "Update QL package done"


scp -r "${SOURCE}/x-pack/plugin/sql/src/main/java/org/elasticsearch/xpack/sql/" "${TARGET}/sql/"
scp -r "${SOURCE}/x-pack/plugin/sql/sql-action/src/main/java/org/elasticsearch/xpack/sql/" "${TARGET}/sql/"
scp -r "${SOURCE}/x-pack/plugin/sql/sql-proto/src/main/java/org/elasticsearch/xpack/sql/" "${TARGET}/sql/"

#rm -rf "${TARGET}/core/*"
#scp -r "${SOURCE}/x-pack/plugin/core/src/main/java/org/elasticsearch/xpack/core/" "${TARGET}/core/"
#echo "Update SQL package done"

$SHELL