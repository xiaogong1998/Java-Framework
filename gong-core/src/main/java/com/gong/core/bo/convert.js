export const listConvertOptions = (list) => {
  return list.map((item) => {
    const temp = {};
    temp['label'] = item.name;
    temp['value'] = item.id;
    return temp;
  });
};

export function listConvertTree(list, disabledFun) {
  const root = [];
  if (list && list.length) {
    const group = {};
    const nList = list.map((item) => {
      const { id, parentId, name, key } = item;
      const nItem = {
        key: key || `${id}`,
        value: id,
        title: name,
        parentId,
        origin: item,
        disabled: disabledFun ? disabledFun(item) : false,
      };
      group[item.id] = nItem;
      return nItem;
    });

    nList.forEach((item) => {
      if (item.parentId !== null && item.parentId !== undefined && group[item.parentId]) {
        const parent = group[item.parentId];
        if (!parent.children) {
          parent.children = [];
        }
        parent.children.push(item);
      } else {
        root.push(item);
      }
    });
  }
  return setLevel(root);
}

function setLevel(list, level = 1) {
  if (list && list.length) {
    const nList = list.map((item) => {
      const { children, origin } = item;
      const nItem = {
        ...item,
        level,
        origin: { ...origin, level },
      };
      if (item.children) {
        nItem.children = setLevel(children, level + 1);
      }
      return nItem;
    });
    return nList;
  }
  return list;
}

// Cascader转换
export function convertToCascader(data) {
  if (!data) {
    return [];
  }
  return data.map((item) => {
    const nItem = {
      value: String(item.id),
      label: item.name,
    };
    if (item.children) {
      nItem.children = convertToCascader(item.children);
    }
    return nItem;
  });
}

// Cascader转换
export function convertListToCascader(list, disabledFun) {
  const root = [];
  if (list && list.length > 0) {
    const group = {};
    const nList = list.map((item) => {
      const { id, parentId, name, key } = item;
      const nItem = {
        key: key || `${id}`,
        value: String(id),
        label: name,
        parentId,
        origin: item,
        disabled: disabledFun ? disabledFun(item) : false,
      };
      group[item.id] = nItem;
      return nItem;
    });

    nList.forEach((item) => {
      if (item.parentId !== null && item.parentId !== undefined && group[item.parentId]) {
        const parent = group[item.parentId];
        if (!parent.children) {
          parent.children = [];
        }
        parent.children.push(item);
      } else {
        root.push(item);
      }
    });
  }
  return root;
}

// 状态检测Cascader转换
export function StatusDetectionListToCascader2(data) {
  if (!data) {
    return [];
  }
  return data.map((item) => {
    const nItem = {
      value: item.name,
      label: item.name,
    };
    return nItem;
  });
}

// 状态检测Cascader转换
export function StatusDetectionListToCascader(data) {
  if (!data) {
    return [];
  }
  return data.map((item) => {
    const nItem = {
      value: String(item.deviceId),
      label: item.deviceName,
    };
    if (item.list) {
      nItem.children = StatusDetectionListToCascader2(item.list);
    }
    return nItem;
  });
}
