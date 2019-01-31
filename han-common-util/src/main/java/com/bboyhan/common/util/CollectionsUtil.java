package com.bboyhan.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/29 18:40
 */
public class CollectionsUtil {

    public static final String DESC = "desc";
	public static final String ASC = "asc";
 
	/**
	 * 对list中的元素按升序排列.
	 * 
	 * @param list 排序集合
	 * @param field 排序字段
	 * @return
	 */
	public static List<?> sort(List<?> list, final String field) {
		return sort(list, field, null);
	}
 
	/**
	 * 对list中的元素进行排序.
	 * 
	 * @param list 排序集合
	 * @param field 排序字段
	 * @param sort 排序方式: SortList.DESC(降序) SortList.ASC(升序).
	 */
	@SuppressWarnings("unchecked")
	public static List<?> sort(List<?> list, final String field,
                               final String sort) {
		Collections.sort(list, new Comparator() {
			public int compare(Object a, Object b) {
				int ret = 0;
				try {
					Field f = a.getClass().getDeclaredField(field);
					f.setAccessible(true);
					Class<?> type = f.getType();
 
					if (type == int.class) {
						ret = Integer.compare(f.getInt(a), f
								.getInt(b));
					} else if (type == double.class) {
						ret = Double.compare(f.getDouble(a), f
								.getDouble(b));
					} else if (type == long.class) {
						ret = Long.compare(f.getLong(a), f
								.getLong(b));
					} else if (type == float.class) {
						ret = Float.compare(f.getFloat(a), f
								.getFloat(b));
					} else if (type == Date.class) {
						ret = ((Date) f.get(a)).compareTo((Date) f.get(b));
					} else if (isImplementsOf(type, Comparable.class)) {
						ret = ((Comparable) f.get(a)).compareTo(f
								.get(b));
					} else {
						ret = String.valueOf(f.get(a)).compareTo(
								String.valueOf(f.get(b)));
					}
 
				} catch (SecurityException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
				}
				if (sort != null && sort.equalsIgnoreCase(DESC)) {
					return -ret;
				} else {
					return ret;
				}
 
			}
		});
		return list;
	}
 
	/**
	 * 对list中的元素按fields和sorts进行排序,
	 * fields[i]指定排序字段,sorts[i]指定排序方式.如果sorts[i]为空则默认按升序排列.
	 * 
	 * @param list
	 * @param fields
	 * @param sorts
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<?> sort(List<?> list, String[] fields, String[] sorts) {
		if (fields != null && fields.length > 0) {
			for (int i = fields.length - 1; i >= 0; i--) {
				final String field = fields[i];
				String tmpSort = ASC;
				if (sorts != null && sorts.length > i && sorts[i] != null) {
					tmpSort = sorts[i];
				}
				final String sort = tmpSort;
				Collections.sort(list, new Comparator() {
					public int compare(Object a, Object b) {
						int ret = 0;
						try {
							Field f = a.getClass().getDeclaredField(field);
							f.setAccessible(true);
							Class<?> type = f.getType();
							if (type == int.class) {
								ret = ((Integer) f.getInt(a))
										.compareTo(f.getInt(b));
							} else if (type == double.class) {
								ret = ((Double) f.getDouble(a))
										.compareTo(f.getDouble(b));
							} else if (type == long.class) {
								ret = ((Long) f.getLong(a)).compareTo(f
										.getLong(b));
							} else if (type == float.class) {
								ret = ((Float) f.getFloat(a))
										.compareTo(f.getFloat(b));
							} else if (type == Date.class) {
								ret = ((Date) f.get(a)).compareTo((Date) f
										.get(b));
							} else if (isImplementsOf(type, Comparable.class)) {
								ret = ((Comparable) f.get(a))
										.compareTo(f.get(b));
							} else {
								ret = String.valueOf(f.get(a)).compareTo(
										String.valueOf(f.get(b)));
							}
 
						} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}

						if (sort != null && sort.equalsIgnoreCase(DESC)) {
							return -ret;
						} else {
							return ret;
						}
					}
				});
			}
		}
		return list;
	}
 
	/**
	 * 默认按正序排列
	 * 
	 * @param list
	 * @param method
	 * @return
	 */
	public static List<?> sortByMethod(List<?> list, final String method) {
		return sortByMethod(list, method, null);
	}
 
	@SuppressWarnings("unchecked")
	public static List<?> sortByMethod(List<?> list, final String method,
                                       final String sort) {
		Collections.sort(list, new Comparator() {
			public int compare(Object a, Object b) {
				int ret = 0;
				try {
					Method m = a.getClass().getMethod(method, null);
					m.setAccessible(true);
					Class<?> type = m.getReturnType();
					if (type == int.class) {
						ret = ((Integer) m.invoke(a, null))
								.compareTo((Integer) m.invoke(b, null));
					} else if (type == double.class) {
						ret = ((Double) m.invoke(a, null)).compareTo((Double) m
								.invoke(b, null));
					} else if (type == long.class) {
						ret = ((Long) m.invoke(a, null)).compareTo((Long) m
								.invoke(b, null));
					} else if (type == float.class) {
						ret = ((Float) m.invoke(a, null)).compareTo((Float) m
								.invoke(b, null));
					} else if (type == Date.class) {
						ret = ((Date) m.invoke(a, null)).compareTo((Date) m
								.invoke(b, null));
					} else if (isImplementsOf(type, Comparable.class)) {
						ret = ((Comparable) m.invoke(a, null))
								.compareTo(m.invoke(b, null));
					} else {
						ret = String.valueOf(m.invoke(a, null)).compareTo(
								String.valueOf(m.invoke(b, null)));
					}
 
					if (isImplementsOf(type, Comparable.class)) {
						ret = ((Comparable) m.invoke(a, null))
								.compareTo(m.invoke(b, null));
					} else {
						ret = String.valueOf(m.invoke(a, null)).compareTo(
								String.valueOf(m.invoke(b, null)));
					}
 
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ne) {
					ne.printStackTrace();
				}

				if (sort != null && sort.toLowerCase().equals(DESC)) {
					return -ret;
				} else {
					return ret;
				}
			}
		});
		return list;
	}
 
	@SuppressWarnings("unchecked")
	public static List<?> sortByMethod(List<?> list, final String methods[],
                                       final String sorts[]) {
		if (methods != null && methods.length > 0) {
			for (int i = methods.length - 1; i >= 0; i--) {
				final String method = methods[i];
				String tmpSort = ASC;
				if (sorts != null && sorts.length > i && sorts[i] != null) {
					tmpSort = sorts[i];
				}
				final String sort = tmpSort;
				Collections.sort(list, new Comparator() {
					public int compare(Object a, Object b) {
						int ret = 0;
						try {
							Method m = a.getClass().getMethod(method, null);
							m.setAccessible(true);
							Class<?> type = m.getReturnType();
							if (type == int.class) {
								ret = ((Integer) m.invoke(a, null))
										.compareTo((Integer) m.invoke(b, null));
							} else if (type == double.class) {
								ret = ((Double) m.invoke(a, null))
										.compareTo((Double) m.invoke(b, null));
							} else if (type == long.class) {
								ret = ((Long) m.invoke(a, null))
										.compareTo((Long) m.invoke(b, null));
							} else if (type == float.class) {
								ret = ((Float) m.invoke(a, null))
										.compareTo((Float) m.invoke(b, null));
							} else if (type == Date.class) {
								ret = ((Date) m.invoke(a, null))
										.compareTo((Date) m.invoke(b, null));
							} else if (isImplementsOf(type, Comparable.class)) {
								ret = ((Comparable) m.invoke(a, null))
										.compareTo(m.invoke(b,
												null));
							} else {
								ret = String.valueOf(m.invoke(a, null))
										.compareTo(
												String.valueOf(m
														.invoke(b, null)));
							}
 
						} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ne) {
							ne.printStackTrace();
						}

						if (sort != null && sort.toLowerCase().equals(DESC)) {
							return -ret;
						} else {
							return ret;
						}
					}
				});
			}
		}
		return list;
	}
 
	/**
	 * 判断对象实现的所有接口中是否包含szInterface
	 * 
	 * @param clazz
	 * @param szInterface
	 * @return
	 */
	public static boolean isImplementsOf(Class<?> clazz, Class<?> szInterface) {
		boolean flag = false;
 
		Class<?>[] face = clazz.getInterfaces();
		for (Class<?> c : face) {
			if (c == szInterface) {
				flag = true;
			} else {
				flag = isImplementsOf(c, szInterface);
			}
		}

		if (!flag && null != clazz.getSuperclass()) {
			return isImplementsOf(clazz.getSuperclass(), szInterface);
		}
		return flag;
	}
 
	/*public static void main(String[] args) throws Exception {
		List<Person> list = new ArrayList<Person>();
 
		list.add(new Person("zhangsan", "b", 18));
		list.add(new Person("zhangsi", "b", 28));
		list.add(new Person("lisi", "d", 20));
		list.add(new Person("wangwu", "d", 22));
		System.out.println("-----------排序前---------------");
		for (Person p : list) {
			System.out.println(p.toString());
		}
		System.out.println();
		// 按age正序排序,注意结果排完后是1,2,3,11. 不是1,11,2,3(如果是String类型正序排序是这样)
		CollectionsUtil.sort(list, "age", null);
		System.out.println("---------测试Integer和正序,按age正序排序-----------------");
		for (Person p : list) {
			System.out.println(p.toString());
		}
		System.out.println();
		// 按id倒序
		CollectionsUtil.sort(list, "userName", CollectionsUtil.DESC);
		System.out.println("--------测试int和倒序,按id倒序------------------");
		for (Person p : list) {
			System.out.println(p.toString());
		}
		System.out.println();

		// 先按userName正序排序,再按age正序排序
		CollectionsUtil.sort(list, new String[] { "userName", "age" }, new String[] {});
		System.out
				.println("---------测试多个排序字段,先按userName正序,userName相同时再按age正序-----------------");
		for (Person p : list) {
			System.out.println(p.toString());
		}
		System.out.println();

		// 先按userName正序排序,再按id倒序排序
		CollectionsUtil.sort(list, new String[] { "userName", "age" }, new String[] {
				CollectionsUtil.ASC, CollectionsUtil.DESC });
		System.out
				.println("---------测试多个排序字段,先按userName正序,userName相同时再按age倒序-----------------");
		for (Person p : list) {
			System.out.println(p.toString());
		}
		System.out.println();
 
		// sortByMethod
		CollectionsUtil.sortByMethod(list, "getAge", null);
		System.out
				.println("---------测试sortByMethod,按getAge方法正序-----------------");
		for (Person p : list) {
			System.out.println(p.toString());
		}
		System.out.println();
	}

	public static class Person {
		private String userName;
		private String password;
		private Integer age;

		@Override
		public String toString() {
			return "Person{" +
					"userName='" + userName + '\'' +
					", password='" + password + '\'' +
					", age=" + age +
					'}';
		}

		public Person() {
		}

		public Person(String userName, String password, Integer age) {
			this.userName = userName;
			this.password = password;
			this.age = age;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}*/
}
