package rxf.server.gen;

// generated

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import rxf.server.*;
import rxf.server.an.DbKeys;

import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static rxf.server.BlobAntiPatternObject.avoidStarvation;

/**
 * generated drivers
 */
public interface CouchDriver {
  Gson GSON =
      new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").setFieldNamingPolicy(
          FieldNamingPolicy.IDENTITY).setPrettyPrinting().create();
  TimeUnit defaultCollectorTimeUnit = TimeUnit.SECONDS;

  //generated items

  public class DbCreate extends DbKeysBuilder {
    private static final int parmsCount = 1;

    private DbCreate() {
    }

    public static DbCreate

    $() {
      return new DbCreate();
    }

    public DbCreateActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new DbCreateActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db]");
    }

    public DbCreate db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public interface DbCreateTerminalBuilder extends TerminalBuilder {
      CouchTx tx();

      @Deprecated
      void oneWay();
    }

    public class DbCreateActionBuilder extends ActionBuilder {
      public DbCreateActionBuilder() {
        super();
      }

      public DbCreateTerminalBuilder fire() {
        return new DbCreateTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.DbCreate.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public CouchTx tx() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(future.get()).toString(),
                  CouchTx.class);
            } catch (Exception e) {
              if (rxf.server.BlobAntiPatternObject.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return null;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();
            final ActionBuilder actionBuilder = ActionBuilder.get();
            BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }
        };
      }

      public DbCreateActionBuilder state(Rfc822HeaderState state) {
        return (DbCreateActionBuilder) super.state(state);
      }

      public DbCreateActionBuilder key(java.nio.channels.SelectionKey key) {
        return (DbCreateActionBuilder) super.key(key);
      }
    }

  }

  public class DbDelete extends DbKeysBuilder {
    private static final int parmsCount = 1;

    private DbDelete() {
    }

    public static DbDelete

    $() {
      return new DbDelete();
    }

    public DbDeleteActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new DbDeleteActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db]");
    }

    public DbDelete db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public interface DbDeleteTerminalBuilder extends TerminalBuilder {
      CouchTx tx();

      @Deprecated
      void oneWay();
    }

    public class DbDeleteActionBuilder extends ActionBuilder {
      public DbDeleteActionBuilder() {
        super();
      }

      public DbDeleteTerminalBuilder fire() {
        return new DbDeleteTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.DbDelete.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public CouchTx tx() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(future.get()).toString(),
                  CouchTx.class);
            } catch (Exception e) {
              if (rxf.server.BlobAntiPatternObject.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return null;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();
            final ActionBuilder actionBuilder = ActionBuilder.get();
            BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }
        };
      }

      public DbDeleteActionBuilder state(Rfc822HeaderState state) {
        return (DbDeleteActionBuilder) super.state(state);
      }

      public DbDeleteActionBuilder key(java.nio.channels.SelectionKey key) {
        return (DbDeleteActionBuilder) super.key(key);
      }
    }

  }

  public class DocFetch extends DbKeysBuilder {
    private static final int parmsCount = 2;

    private DocFetch() {
    }

    public static DocFetch

    $() {
      return new DocFetch();
    }

    public DocFetchActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new DocFetchActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db, docId]");
    }

    public DocFetch db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public DocFetch docId(java.lang.String stringParam) {
      parms.put(DbKeys.etype.docId, stringParam);
      return this;
    }

    public interface DocFetchTerminalBuilder extends TerminalBuilder {
      java.nio.ByteBuffer pojo();

      Future<ByteBuffer> future();

      String json();
    }

    public class DocFetchActionBuilder extends ActionBuilder {
      public DocFetchActionBuilder() {
        super();
      }

      public DocFetchTerminalBuilder fire() {
        return new DocFetchTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.DocFetch.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public java.nio.ByteBuffer pojo() {
            try {
              return future.get();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public String json() {
            try {
              ByteBuffer visit = future.get();
              return null == visit ? null : one.xio.HttpMethod.UTF8.decode(avoidStarvation(visit))
                  .toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }
        };
      }

      public DocFetchActionBuilder state(Rfc822HeaderState state) {
        return (DocFetchActionBuilder) super.state(state);
      }

      public DocFetchActionBuilder key(java.nio.channels.SelectionKey key) {
        return (DocFetchActionBuilder) super.key(key);
      }
    }

  }

  public class RevisionFetch extends DbKeysBuilder {
    private static final int parmsCount = 2;

    private RevisionFetch() {
    }

    public static RevisionFetch

    $() {
      return new RevisionFetch();
    }

    public RevisionFetchActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new RevisionFetchActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db, docId]");
    }

    public RevisionFetch db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public RevisionFetch docId(java.lang.String stringParam) {
      parms.put(DbKeys.etype.docId, stringParam);
      return this;
    }

    public interface RevisionFetchTerminalBuilder extends TerminalBuilder {
      String json();

      Future<ByteBuffer> future();
    }

    public class RevisionFetchActionBuilder extends ActionBuilder {
      public RevisionFetchActionBuilder() {
        super();
      }

      public RevisionFetchTerminalBuilder fire() {
        return new RevisionFetchTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.RevisionFetch.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public String json() {
            try {
              ByteBuffer visit = future.get();
              return null == visit ? null : one.xio.HttpMethod.UTF8.decode(avoidStarvation(visit))
                  .toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }

          public Future<ByteBuffer> future() {
            return future;
          }
        };
      }

      public RevisionFetchActionBuilder state(Rfc822HeaderState state) {
        return (RevisionFetchActionBuilder) super.state(state);
      }

      public RevisionFetchActionBuilder key(java.nio.channels.SelectionKey key) {
        return (RevisionFetchActionBuilder) super.key(key);
      }
    }

  }

  public class DocPersist extends DbKeysBuilder {
    private static final int parmsCount = 2;

    private DocPersist() {
    }

    public static DocPersist

    $() {
      return new DocPersist();
    }

    public DocPersistActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new DocPersistActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db, validjson]");
    }

    public DocPersist db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public DocPersist validjson(java.lang.String stringParam) {
      parms.put(DbKeys.etype.validjson, stringParam);
      return this;
    }

    public DocPersist docId(java.lang.String stringParam) {
      parms.put(DbKeys.etype.docId, stringParam);
      return this;
    }

    public DocPersist rev(java.lang.String stringParam) {
      parms.put(DbKeys.etype.rev, stringParam);
      return this;
    }

    public interface DocPersistTerminalBuilder extends TerminalBuilder {
      CouchTx tx();

      @Deprecated
      void oneWay();

      Future<ByteBuffer> future();
    }

    public class DocPersistActionBuilder extends ActionBuilder {
      public DocPersistActionBuilder() {
        super();
      }

      public DocPersistTerminalBuilder fire() {
        return new DocPersistTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.DocPersist.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public CouchTx tx() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(future.get()).toString(),
                  CouchTx.class);
            } catch (Exception e) {
              if (rxf.server.BlobAntiPatternObject.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return null;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();
            final ActionBuilder actionBuilder = ActionBuilder.get();
            BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }

          public Future<ByteBuffer> future() {
            return future;
          }
        };
      }

      public DocPersistActionBuilder state(Rfc822HeaderState state) {
        return (DocPersistActionBuilder) super.state(state);
      }

      public DocPersistActionBuilder key(java.nio.channels.SelectionKey key) {
        return (DocPersistActionBuilder) super.key(key);
      }
    }

  }

  public class DocDelete extends DbKeysBuilder {
    private static final int parmsCount = 3;

    private DocDelete() {
    }

    public static DocDelete

    $() {
      return new DocDelete();
    }

    public DocDeleteActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new DocDeleteActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db, docId, rev]");
    }

    public DocDelete db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public DocDelete docId(java.lang.String stringParam) {
      parms.put(DbKeys.etype.docId, stringParam);
      return this;
    }

    public DocDelete rev(java.lang.String stringParam) {
      parms.put(DbKeys.etype.rev, stringParam);
      return this;
    }

    public interface DocDeleteTerminalBuilder extends TerminalBuilder {
      CouchTx tx();

      @Deprecated
      void oneWay();

      Future<ByteBuffer> future();
    }

    public class DocDeleteActionBuilder extends ActionBuilder {
      public DocDeleteActionBuilder() {
        super();
      }

      public DocDeleteTerminalBuilder fire() {
        return new DocDeleteTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.DocDelete.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public CouchTx tx() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(future.get()).toString(),
                  CouchTx.class);
            } catch (Exception e) {
              if (rxf.server.BlobAntiPatternObject.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return null;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();
            final ActionBuilder actionBuilder = ActionBuilder.get();
            BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }

          public Future<ByteBuffer> future() {
            return future;
          }
        };
      }

      public DocDeleteActionBuilder state(Rfc822HeaderState state) {
        return (DocDeleteActionBuilder) super.state(state);
      }

      public DocDeleteActionBuilder key(java.nio.channels.SelectionKey key) {
        return (DocDeleteActionBuilder) super.key(key);
      }
    }

  }

  public class DesignDocFetch extends DbKeysBuilder {
    private static final int parmsCount = 2;

    private DesignDocFetch() {
    }

    public static DesignDocFetch

    $() {
      return new DesignDocFetch();
    }

    public DesignDocFetchActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new DesignDocFetchActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db, designDocId]");
    }

    public DesignDocFetch db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public DesignDocFetch designDocId(java.lang.String stringParam) {
      parms.put(DbKeys.etype.designDocId, stringParam);
      return this;
    }

    public interface DesignDocFetchTerminalBuilder extends TerminalBuilder {
      java.nio.ByteBuffer pojo();

      Future<ByteBuffer> future();

      String json();
    }

    public class DesignDocFetchActionBuilder extends ActionBuilder {
      public DesignDocFetchActionBuilder() {
        super();
      }

      public DesignDocFetchTerminalBuilder fire() {
        return new DesignDocFetchTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.DesignDocFetch.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public java.nio.ByteBuffer pojo() {
            try {
              return future.get();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public String json() {
            try {
              ByteBuffer visit = future.get();
              return null == visit ? null : one.xio.HttpMethod.UTF8.decode(avoidStarvation(visit))
                  .toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }
        };
      }

      public DesignDocFetchActionBuilder state(Rfc822HeaderState state) {
        return (DesignDocFetchActionBuilder) super.state(state);
      }

      public DesignDocFetchActionBuilder key(java.nio.channels.SelectionKey key) {
        return (DesignDocFetchActionBuilder) super.key(key);
      }
    }

  }

  public class ViewFetch extends DbKeysBuilder {
    private static final int parmsCount = 2;

    private ViewFetch() {
    }

    public static ViewFetch

    $() {
      return new ViewFetch();
    }

    public ViewFetchActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new ViewFetchActionBuilder();
      throw new IllegalArgumentException("required parameters are: [db, view]");
    }

    public ViewFetch db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public ViewFetch view(java.lang.String stringParam) {
      parms.put(DbKeys.etype.view, stringParam);
      return this;
    }

    public ViewFetch type(java.lang.Class classParam) {
      parms.put(DbKeys.etype.type, classParam);
      return this;
    }

    public interface ViewFetchTerminalBuilder extends TerminalBuilder {
      rxf.server.CouchResultSet rows();

      Future<ByteBuffer> future();

      void continuousFeed();

    }

    public class ViewFetchActionBuilder extends ActionBuilder {
      public ViewFetchActionBuilder() {
        super();
      }

      public ViewFetchTerminalBuilder fire() {
        return new ViewFetchTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.ViewFetch.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public rxf.server.CouchResultSet rows() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(avoidStarvation(future.get()))
                  .toString(), new java.lang.reflect.ParameterizedType() {
                public Type getRawType() {
                  return CouchResultSet.class;
                }

                public Type getOwnerType() {
                  return null;
                }

                public Type[] getActualTypeArguments() {
                  Type[] t = {(Type) ViewFetch.this.get(DbKeys.etype.type)};
                  return t;
                }
              });
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public void continuousFeed() {
            throw new AbstractMethodError();
          }
        };
      }

      public ViewFetchActionBuilder state(Rfc822HeaderState state) {
        return (ViewFetchActionBuilder) super.state(state);
      }

      public ViewFetchActionBuilder key(java.nio.channels.SelectionKey key) {
        return (ViewFetchActionBuilder) super.key(key);
      }
    }

  }

  public class JsonSend extends DbKeysBuilder {
    private static final int parmsCount = 2;

    private JsonSend() {
    }

    public static JsonSend

    $() {
      return new JsonSend();
    }

    public JsonSendActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new JsonSendActionBuilder();
      throw new IllegalArgumentException("required parameters are: [opaque, validjson]");
    }

    public JsonSend opaque(java.lang.String stringParam) {
      parms.put(DbKeys.etype.opaque, stringParam);
      return this;
    }

    public JsonSend validjson(java.lang.String stringParam) {
      parms.put(DbKeys.etype.validjson, stringParam);
      return this;
    }

    public JsonSend type(java.lang.Class classParam) {
      parms.put(DbKeys.etype.type, classParam);
      return this;
    }

    public interface JsonSendTerminalBuilder extends TerminalBuilder {
      CouchTx tx();

      @Deprecated
      void oneWay();

      rxf.server.CouchResultSet rows();

      String json();

      Future<ByteBuffer> future();

      void continuousFeed();

    }

    public class JsonSendActionBuilder extends ActionBuilder {
      public JsonSendActionBuilder() {
        super();
      }

      public JsonSendTerminalBuilder fire() {
        return new JsonSendTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.JsonSend.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public CouchTx tx() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(future.get()).toString(),
                  CouchTx.class);
            } catch (Exception e) {
              if (rxf.server.BlobAntiPatternObject.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return null;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();
            final ActionBuilder actionBuilder = ActionBuilder.get();
            BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }

          public rxf.server.CouchResultSet rows() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(avoidStarvation(future.get()))
                  .toString(), new java.lang.reflect.ParameterizedType() {
                public Type getRawType() {
                  return CouchResultSet.class;
                }

                public Type getOwnerType() {
                  return null;
                }

                public Type[] getActualTypeArguments() {
                  Type[] t = {(Type) JsonSend.this.get(DbKeys.etype.type)};
                  return t;
                }
              });
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }

          public String json() {
            try {
              ByteBuffer visit = future.get();
              return null == visit ? null : one.xio.HttpMethod.UTF8.decode(avoidStarvation(visit))
                  .toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return null;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public void continuousFeed() {
            throw new AbstractMethodError();
          }
        };
      }

      public JsonSendActionBuilder state(Rfc822HeaderState state) {
        return (JsonSendActionBuilder) super.state(state);
      }

      public JsonSendActionBuilder key(java.nio.channels.SelectionKey key) {
        return (JsonSendActionBuilder) super.key(key);
      }
    }

  }

  public class BlobSend extends DbKeysBuilder {
    private static final int parmsCount = 5;

    private BlobSend() {
    }

    public static BlobSend

    $() {
      return new BlobSend();
    }

    public BlobSendActionBuilder to() {
      if (parms.size() >= parmsCount)
        return new BlobSendActionBuilder();
      throw new IllegalArgumentException(
          "required parameters are: [blob, db, docId, rev, attachname]");
    }

    public BlobSend blob(java.nio.ByteBuffer bytebufferParam) {
      parms.put(DbKeys.etype.blob, bytebufferParam);
      return this;
    }

    public BlobSend db(java.lang.String stringParam) {
      parms.put(DbKeys.etype.db, stringParam);
      return this;
    }

    public BlobSend docId(java.lang.String stringParam) {
      parms.put(DbKeys.etype.docId, stringParam);
      return this;
    }

    public BlobSend rev(java.lang.String stringParam) {
      parms.put(DbKeys.etype.rev, stringParam);
      return this;
    }

    public BlobSend attachname(java.lang.String stringParam) {
      parms.put(DbKeys.etype.attachname, stringParam);
      return this;
    }

    public BlobSend mimetypeEnum(one.xio.MimeType mimetypeParam) {
      parms.put(DbKeys.etype.mimetypeEnum, mimetypeParam);
      return this;
    }

    public BlobSend mimetype(java.lang.String stringParam) {
      parms.put(DbKeys.etype.mimetype, stringParam);
      return this;
    }

    public interface BlobSendTerminalBuilder extends TerminalBuilder {
      CouchTx tx();

      Future<ByteBuffer> future();

      @Deprecated
      void oneWay();
    }

    public class BlobSendActionBuilder extends ActionBuilder {
      public BlobSendActionBuilder() {
        super();
      }

      public BlobSendTerminalBuilder fire() {
        return new BlobSendTerminalBuilder() {
          Future<ByteBuffer> future =
              BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
                final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();
                final ActionBuilder actionBuilder = (ActionBuilder) ActionBuilder.get();

                public java.nio.ByteBuffer call() throws Exception {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  return rxf.server.driver.CouchMetaDriver.BlobSend.visit(dbKeysBuilder,
                      actionBuilder);
                }
              });

          public CouchTx tx() {
            try {
              return GSON.fromJson(one.xio.HttpMethod.UTF8.decode(future.get()).toString(),
                  CouchTx.class);
            } catch (Exception e) {
              if (rxf.server.BlobAntiPatternObject.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return null;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();
            final ActionBuilder actionBuilder = ActionBuilder.get();
            BlobAntiPatternObject.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);
                  ActionBuilder.currentAction.set(actionBuilder);
                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }
        };
      }

      public BlobSendActionBuilder state(Rfc822HeaderState state) {
        return (BlobSendActionBuilder) super.state(state);
      }

      public BlobSendActionBuilder key(java.nio.channels.SelectionKey key) {
        return (BlobSendActionBuilder) super.key(key);
      }
    }

  }
}
